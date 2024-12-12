import java.util.*;

public class MentalHealthJournal {
    public static void run(int userId) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        EntryJournal entry = new EntryJournal();
        while (running) {
            System.out.println("\nMental Health Journal");
            System.out.println("1. Add Journal Entry");
            System.out.println("2. View Journal Entries");
            System.out.println("3. Set Daily Goal");
            System.out.println("4. View Daily Goal");
            System.out.println("5. Get Advice or Tip");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter journal entry: ");
                    String content = scanner.nextLine();
                    System.out.print("Enter mood: ");
                    String mood = scanner.nextLine();
                    System.out.print("Enter stress level (1-10): ");
                    int stressLevel = scanner.nextInt();
                    System.out.print("Enter energy level (1-10): ");
                    int energyLevel = scanner.nextInt();
                    entry.addJournalEntry(userId, content, mood, stressLevel, energyLevel);
                    run(userId);
                case 2:
                    System.out.print("Your Journal Entries: \n");
                    entry.viewJournalEntries(userId);
                    run(userId);
                case 3:
                    System.out.print("Enter your daily goal: ");
                    String goal = scanner.nextLine();
                    entry.setDailyGoal(userId, goal);
                    System.out.print("Daily Goal Set.");
                    run(userId);
                case 4:
                    System.out.print("Your Daily Goals: \n");
                    entry.dailyGoals(userId);
                    run(userId);
                case 5:
                    entry.showAdviceOrTip();
                    run(userId);
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }
}

