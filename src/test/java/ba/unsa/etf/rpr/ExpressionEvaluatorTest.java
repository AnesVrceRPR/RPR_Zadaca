package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions. *;

class ExpressionEvaluatorTest {
  /**
   * Testiranje da li metoda evaluate() vraća ispravan rezultat
   */
  @Test
  void testFunkcionalnosti()
  {
    String s = "( 2 + ( 4 * 5 ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertEquals(22, evaluator.evaluate(s));
  }

  /**
   * Testiranje da li se umjesto nekog broja nalazi slovo
   */
  @Test
    void testSlovoUmjestoBroja()
    {
      String s = "( 2 + k )";
      ExpressionEvaluator evaluator = new ExpressionEvaluator();
      assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Neispravan format izraza");
    }

  /**
   * Testiranje da li između svakog karaktera postoji razmak
   */
  @Test
  void testBezRazmaka()
  {
    String s = "( 2+ 4)";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Neispravan format izraza");
  }
  /**
   * Testiranje da li postoji odgovarajuci broj zagrada u odnosu na broj operatora
   */
  @Test
  void testNeadekvatanBrojZagrada()
  {
    String s = "( 2 + ( 4 ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Neispravan format izraza");
  }
  /**
   * Testiranje da li se baca izuzetak kod dijeljenje sa nulom
   */
  @Test
  void testDijeljenjeSaNulom()
  {
    String s = "( 2 / 0 )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Neispravan format izraza");
  }

  /**
   * Testiranje da li se baca izuzetak kada se funkciji sin() proslijedi negativan broj
   */
  @Test
  void testSinusOdNegBroja()
  {
    String s = "( 2 + ( sin ( 2 ) ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Funkciji sin() proslijedjen negativan broj.");
  }

  /**
   * Testiranje da li su zatvorene sve zagrade koje su se otvorile
   */
  @Test
  void testDaLiSuSveZagradeZatvorene()
  {
    String s = "( 2 + 3 (";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s), "Neispravan format izraza");
  }


}