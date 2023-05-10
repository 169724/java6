import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

class WrongStudentName extends Exception {
}

class WrongAge extends Exception {
}

class WrongDateOfBirth extends Exception {
}

class Main {
  public static Scanner scan = new Scanner(System.in);
  public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

  public static void main(String[] args) {
    while (true) {
      try {
        int ex = menu();
        switch (ex) {
          case 1:
            exercise1();
            break;
          case 2:
            exercise2();
            break;
          case 3:
            exercise3();
            break;
          default:
            return;
        }
      } catch (IOException e) {
        System.out.println("Błąd wejścia/wyjścia!");
      } catch (WrongStudentName e) {
        System.out.println("Błędne imie studenta!");
      } catch (WrongAge e) {
        System.out.println(" ");
        System.out.println("Błędny wiek!");
        System.out.println(" ");
        System.out.println("ERROR! ");
        System.out.println(" ");
      } catch (WrongDateOfBirth e) {
        System.out.println("Błędna data urodzenia!");
      }
    }
  }

  public static int menu() {
    System.out.println("Wciśnij:");
    System.out.println("1 - aby dodać studenta");
    System.out.println("2 - aby wypisać wszystkich studentów");
    System.out.println("3 - aby wyszukać studenta po imieniu");
    System.out.println("0 - aby wyjść z programu");
    return scan.nextInt();
  }

  public static String ReadName() throws WrongStudentName {
    scan.nextLine();
    System.out.println("Podaj imie: ");
    String name = scan.nextLine();
    if (name.contains(" ")) {
      throw new WrongStudentName();
    }
    return name;
  }

  public static int ReadAge() throws WrongAge {
    System.out.println("Podaj wiek: ");
    int age = scan.nextInt();
    if (age < 0 || age > 100) {
      throw new WrongAge();
    }
    return age;
  }

 public static String ReadDateOfBirth() throws WrongDateOfBirth {
        scan.nextLine();
        System.out.println("Podaj datę urodzenia w formacie DD-1
                           MM-YYYY:");
        String dateStr = scan.nextLine();
        try {
            Date date = dateFormat.parse(dateStr);
            if (!dateFormat.format(date).equals(dateStr)) {
                throw new WrongDateOfBirth();
            }
            return dateStr;
        } catch (ParseException e) {
            throw new WrongDateOfBirth();
        }
    }

  public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth {
    var name = ReadName();
    var age = ReadAge();
    var date = ReadDateOfBirth();
    (new Service()).addStudent(new Student(name, age, date));
  }

  public static void exercise2() throws IOException {
    var students = (new Service()).getStudents();
    for (Student current : students) {
      System.out.println(current.ToString());
    }
  }

  public static void exercise3() throws IOException {
    scan.nextLine();
    System.out.println("Podaj imie: ");
    var name = scan.nextLine();
    var wanted = (new Service()).findStudentByName(name);
    if (wanted == null)
      System.out.println("Nie znaleziono...");
    else {
      System.out.println("Znaleziono: ");
      System.out.println(wanted.ToString());
    }
  }
}
