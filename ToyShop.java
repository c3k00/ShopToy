import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ToyShop {
    private String[] ids;
    private String[] names;
    private int[] weights;

    public ToyShop() {
        ids = new String[3];
        names = new String[3];
        weights = new int[3];
    }

    public void addNewToy() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Введите id игрушки " + (i+1) + ": ");
            ids[i] = scanner.nextLine();
            System.out.println("Введите имя игрушки " + (i+1) + ": ");
            names[i] = scanner.nextLine();
            System.out.println("Введите частоту выпадения(веса) игрушки " + (i+1) + ": ");
            weights[i] = scanner.nextInt();
            scanner.nextLine(); // consume newline
        }
    }

    public void writeToFile() {
        try {
            FileWriter writer = new FileWriter("output.txt");
            PriorityQueue<Toy> toyQueue = new PriorityQueue<>();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < weights[i]; j++) {
                    toyQueue.add(new Toy(ids[i], names[i]));
                }
            }

            for (int i = 0; i < 10; i++) {
                Toy toy = toyQueue.poll();
                writer.write("Игрушка " + (i+1) + ": " + toy.getId() + " - " + toy.getName() + "\n");
            }

            writer.close();
            System.out.println("Данные успешно записаны в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();
        toyShop.addNewToy();
        toyShop.writeToFile();
    }

    private class Toy implements Comparable<Toy> {
        private String id;
        private String name;

        public Toy(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Toy other) {
            return this.id.compareTo(other.id);
        }
    }
}
