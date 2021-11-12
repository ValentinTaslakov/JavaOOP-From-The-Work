package military;

import military.elite.implementations.*;
import military.elite.interfaces.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<Integer, Soldier> army = new LinkedHashMap<>();

        String line = scan.nextLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");

            String type = tokens[0];
            int id = Integer.parseInt(tokens[1]);
            String firstName = tokens[2];
            String lastName = tokens[3];

            switch (type) {
                case "Private":
                    army.putIfAbsent(id, new PrivateImpl(id, firstName, lastName, Double.parseDouble(tokens[4])));
                    break;
                case "LieutenantGeneral":
                    Collection<Private> somePrivates = new ArrayList<>();
                    Stream.of(tokens).skip(5)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList()).forEach(e -> {
                        somePrivates.add((Private) army.get(e));
                    });
                    army.putIfAbsent(id, new LeutenantGeneralImpl(id, firstName, lastName, Double.parseDouble(tokens[4]),
                            somePrivates));
                    break;
                case "Engineer":

                    SpecialisedSoldier engineer = new EngineerImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), tokens[5], parseRepairs(tokens));

                    if (engineer.getCorps() != null)
                        army.putIfAbsent(id, engineer);
                    break;
                case "Commando":

                    SpecialisedSoldier soldier = new CommandoImpl(id, firstName, lastName, Double.parseDouble(tokens[4]), tokens[5], parseMissions(tokens));
                    if (soldier.getCorps() != null)
                        army.putIfAbsent(id, soldier);
                    break;
                case "Spy":
                    army.putIfAbsent(id, new SpyImpl(id, firstName, lastName, tokens[4]));
                    break;

            }
            line = scan.nextLine();
        }

        for (Soldier soldier : army.values()) {
            System.out.println(soldier.toString().trim());
        }
    }

    private static Collection<Repair> parseRepairs(String... args) {
        Collection<Repair> rp = new ArrayList<>();
        for (int i = 6; i < args.length; i += 2) {
            rp.add(new RepairImpl(args[i], Integer.parseInt(args[i + 1])));

        }
        return rp;
    }

    private static Collection<Mission> parseMissions(String... args) {
        Collection<Mission> rp = new ArrayList<>();
        for (int i = 6; i < args.length; i += 2) {
            Mission m = new MissionImpl(args[i], args[i + 1]);
            if (m.getState() != null)
                rp.add(m);


        }
        return rp;
    }
}
