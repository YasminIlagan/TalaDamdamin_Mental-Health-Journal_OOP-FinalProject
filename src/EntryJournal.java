import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntryJournal {
    void addJournalEntry(int userId, String content, String mood, int stressLevel, int energyLevel) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO journal_entries (user_id, content, mood, stress_level, energy_level) VALUES (?, ?, ?, ?, ?)");

            stmt.setInt(1, userId);
            stmt.setString(2, content);
            stmt.setString(3, mood);
            stmt.setInt(4,stressLevel);
            stmt.setInt(5, energyLevel);
            stmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewJournalEntries(int userId) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT entry_date, content, mood, stress_level, energy_level FROM journal_entries WHERE user_id = ?");

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println("Date: " + rs.getDate("entry_date"));
                System.out.println("Content: " + rs.getString("content"));
                System.out.println("Content: " + rs.getString("mood"));
                System.out.println("Content: " + rs.getInt("stress_level"));
                System.out.println("Content: " + rs.getInt("energy_level"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dailyGoals(int userId) {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT goal_date, goal_text FROM daily_goals WHERE user_id = ?");

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                System.out.println("Date: " + rs.getDate("goal_date"));
                System.out.println("Goal: " + rs.getString("goal_text"));
                System.out.println("-------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDailyGoal(int userId, String goal) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO daily_goals (user_id, goal_text) VALUES (?, ?)")) {

            stmt.setInt(1, userId);
            stmt.setString(2, goal);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAdviceOrTip() {
        try(Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT advice_text FROM advice ORDER BY RAND() LIMIT 1");
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                System.out.println("Advice: " + rs.getString("advice_text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

