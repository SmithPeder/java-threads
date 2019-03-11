
public class Output {

  void waiting(String s) {
    System.out.println((char)27 + "[34m" + s);
  }

  void door(String s) {
    System.out.println((char)27 + "[33m" + s);
  }

  void waitress(String s) {
    System.out.println((char)27 + "[32m" + s);
  }

  void red(String s) {
    System.out.println((char)27 + "[31m" + s);
  }

  void clock(String s) {
    System.out.println();
    System.out.println((char)27 + "[0m" + s);
    System.out.println();
  }

  void check() {
    System.out.println();
    System.out.println((char)27 + "[0m" + "Here");
    System.out.println();
  }

  String padRight(String s, int n) {
    return String.format("%1$-" + n + "s", s);
  }
}
