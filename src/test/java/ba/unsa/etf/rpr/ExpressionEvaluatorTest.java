package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa za testiranje klase ExpressionEvaluator. Testovi pokrivaju dosta različitih situacija: Dijeljenje sa nulom, prosljedjivanje negativnog
 * broja funkciji sqrt, slovo u izrazu umjesto broja, razne kombinacije sa zagradma, dupli brojevi/dupli operatori...
 */

public class ExpressionEvaluatorTest {

  /**
   * Testiranje da li metoda evaluate() vraća ispravan rezultat
   */
  @Test
  void testOsnovneFunkcionalnosti()
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
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }

  /**
   * Testiranje da li između svakog karaktera postoji razmak
   */
  @Test
  void testBezRazmaka()
  {
    String s = "( 2+ 4)";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }
  /**
   * Testiranje da li postoji odgovarajuci broj zagrada u odnosu na broj operatora
   */
  @Test
  void testNeadekvatanBrojZagrada()
  {
    String s = "( 2 + ( 4 ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }
  /**
   * Testiranje da li se baca izuzetak kod dijeljenje sa nulom
   */
  @Test
  void testDijeljenjeSaNulom()
  {
    String s = "( 2 / 0 )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    Exception e = assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
    assertTrue(e.getMessage().contains("djeljenje s nulom"));
  }

  /**
   * Testiranje da li metoda baca izuzetak kada se sqrt-u proslijedi negativan broj
   */
  @Test
  void testSqrtOdNegBroja()
  {
    String s = "( 2 + sqrt ( -2 ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();

    Exception e = assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
    assertTrue(e.getMessage().contains("sqrt-u proslijedjen negativan broj."));
  }

  /**
   * Testiranje da li su zatvorene sve otvorene zagrade
   */
  @Test
  void testDaLiSuSveZagradeZatvorene()
  {
    String s = "( 2 + 3 (";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }

  /**
   * Testiranje da li metoda baca izuzetak kada joj je proslijedjen neispravan format sa sinusom
   */
  @Test
  void testNeispravnogFormataSaSinusom()
  {
    String s = "( 1 + ( sqrt ( 4 ) ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }

  /**
   * * Testiranje da li metoda baca izuzetak kada je sinusu proslijedjen parametar koji nije unutar zagarada
   */
  @Test
  void testSinusaBezZagradaZaParametar()
  {
    String s = "( 1 + ( sqrt 4 ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s));
  }

  /**
   * * Testiranje da li funkcija radi ispravno za opsirniji izraz
   */
  @Test
  void testOpsirnijegIzraza()
  {
    String s = "( ( 4 * 5 ) + sqrt ( ( 32 / 2 ) ) )";
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertEquals(24, evaluator.evaluate(s));
  }

  /**
   * * Testiranje da li funkcija baca izuzezak za prazne izraze
   */
  @Test
  void testPraznogString()
  {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    String s1 = "";
    String s2 = "( )";
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s1));
    assertThrows(RuntimeException.class, () -> evaluator.evaluate(s2));
  }

  /**
   * * Testiranje da li funkcija baca izuzetak kada se u izrazu nalaze dva broja koja nisu odvojena ni operatorom ni zagradama
   */
  @Test
  void testDvaUzastopnaBroja()
  {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 4 + ( 1 + 2 2 ) )");});
  }

  /**
   * * Testiranje da li funkcija baca izuzetak kada su u izrazu zagrade nepravilno rasporedjene
   */
  @Test
  void testNepravilneKombinacijeZagrada()
  {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 100 * 5 + ( 4 ) )");});
  }

  /**
   * Testiranje dodatnih slucajeva
   */
  @Test
  void DodatniTest(){
    ExpressionEvaluator evaluator = new ExpressionEvaluator();

    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 2 - ( 3 ++ 5 ) )");});
    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 2 - ( 3 + + 5 ) )");});
    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 5 + 5 );"); });
    assertThrows(RuntimeException.class, () -> {evaluator.evaluate("( 2 - 2 - 2 )");});

  }

}