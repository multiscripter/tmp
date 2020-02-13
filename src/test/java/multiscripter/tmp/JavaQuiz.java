package multiscripter.tmp;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaQuiz {

  /**
   * Вопрос:
   * Какое сравнение выведет: false?
   *
   * Ответ:
   * Все сравнения дают false кроме ошибочного Compile error.
   *
   * Объяснение:
   * В StringBuilder не определён метод equals().
   */
  @Test
  public void quest1() {
    String string = "hello";
    StringBuilder builder = new StringBuilder("hello");
    StringBuilder builder2 = new StringBuilder("hello");

    //System.out.println(string == builder);// Compile error: incomparable types: java.lang.String and java.lang.StringBuilder
    System.out.println(string == builder.toString()); // false.
    System.out.println(string.equals(builder)); // false.
    System.out.println(builder.equals(builder2)); // false.
  }

  /**
   * Вопрос:
   * Какой метод вызовется?
   *
   * Ответ:
   * action(long l1, long l2).
   *
   * Объяснение:
   * 1. Сначала расширить тип и попататься найти подходящий метод.
   * 2. Если нет подходящего метода с параметрами расширенного типа,
   * то попататься метод с типами-обёртками.
   * 3. Если нет подходящего метода с типами-обёртками,
   * то использовать попататься найти метод с var... args.
   */
  @Test
  public void quest2() {
    byte b = 1;
    action(b, b);
  }

  public static void action(byte... args) {
    System.out.println("action(byte... args)");
  }

  public static void action(long l1, long l2) {
    System.out.println("action(long l1, long l2)");
  }

  public static void action(Byte b1, Byte b2) {
    System.out.println("action(Byte l1, Byte l2)");
  }

  /**
   * Вопрос:
   * Какой будет результат работы программы?
   *
   * Ответ:
   * Будет выведена строка: quest3
   *
   * Объяснение:
   * http: - это метка.
   * //google.ru - это комментарий.
   */
  @Test
  public void quest3() {
    quest3run();
  }

  private void quest3run() {
    System.out.println("quest3");
    http://google.ru
    return;
  }

  /**
   * Вопрос:
   * Какие сравнения напечатают true?
   *
   * Ответ:
   * 1 и 3.
   *
   * Объяснение:
   * Первое сравнение даст true.
   * Когда определяется s1, то "hello" попадает в пул констант.
   * Когда будет определяться s4, то значение будет взято из пула констант.
   */
  @Test
  public void quest4() {
    String s1 = "hello";
    final String s2 = "hel";
    String s3 = "lo";
    String s4 = "hello";

    // true. 2 ссылки на одну строку.
    assertTrue(s1 == s4);
    // false. s2 + s3 создадёт новую строковую константу с новой ссылкой.
    assertFalse(s1 == s2 + s3);
    // true. На этапе компиляции сначала будут вычислены финальная переменная и строковая константа.
    // Затем, после конкатенации, компилятор вычислит строковую константу "hello".
    // Так как такая константа уже есть в пуле, то ссылка на неё будет подставлена
    // в качестве правого операнда оператора сравнения.
    assertTrue(s1 == s2 + "lo");
  }

  /**
   * Вопрос:
   * Какие сравнения напечатают true?
   *
   * Ответ:
   * 1.
   *
   * Объяснение:
   * Целочисленные значения в диапазоне -128 - 127 берутся из кэша.
   * Соответственно переменным a и b будет присвоена одна и та же ссылка.
   */
  @Test
  public void quest5() {
    Long a = 111L;
    Long b = 111L;
    Long c = 222L;
    Long d = 222L;

    assertTrue(a == b);
    assertFalse(c == d);
  }

  /**
   * Вопрос:
   * Каким будет результат вызова метода work()?
   *
   * Ответ:
   * Программа зависнет.
   *
   * Объяснение:
   * 1. work() будет вызываться рекурсивно пока стэк вызовов не переполнится.
   * 2. Будет выброшено исключение StackOverflowError.
   * 3. Выполнится блок finally, стэк опять переподнится. Далее пункт 2.
   */
  @Test
  public void quest6() {
    //work();
  }
  private void work() {
    try {
      work();
    } finally {
      work();
    }
  }

  /**
   * Вопрос:
   * Какой будет результат выполнения кода?
   *
   * Ответ:
   * ArithmeticException.
   *
   * Объяснение:
   * 1. Вычисляется arr[--i]. i = 1. Значение 1 запоминается.
   * Вычисляется выражение справа от оператора "=".
   * 2. i = 0.
   * 3. 1 / 0 выбрасывает ArithmeticException.
   */
  @Test
  public void quest7() {
    Exception exception = assertThrows(ArithmeticException.class, () -> {
      int[] arr = new int[1];
      int i = 2;
      arr[--i] = 1 / --i;
    });
    assertEquals("java.lang.ArithmeticException", exception.getClass().getName());
  }

  /**
   * Вопрос:
   * Какой будет вывод на консоль?
   *
   * Ответ:
   * Thread end.Main end
   *
   * Объяснение:
   * 1. При завершении поток вызывает свой notifyAll().
   * 2. Вызвавший поток выходит из ожидания.
   */
  @Test
  public void quest8() throws InterruptedException {
    Thread t = new Thread(() -> System.out.print("Thread end."));

    synchronized (t) {
      t.start();
      t.wait();
    }

    System.out.println("Main end");
  }

  /**
   * Вопрос:
   * Какой будет вывод на консоль?
   *
   * Ответ:
   * ClassCastException.
   *
   * Объяснение:
   * Чтобы правильно разложить элементы по дереву компаратор вызывает метод
   * compareTo(T item). "T" принимает тип элемента, укоторого вызван метод.
   * В данном случае Number<Integer>.compareTo(Integer item).
   * Так как в параметре передаётся long, выбрасывается ClassCastException.
   */
  @Test
  public void quest9() {
    Exception exception = assertThrows(ClassCastException.class, () -> {
      Set<Number> set = new TreeSet<>();
      set.add(1);
      set.add(1L);
      set.add(1.0);

      System.out.println(set.size());
    });
    assertEquals("java.lang.ClassCastException", exception.getClass().getName());
  }

  /**
   * Вопрос:
   * Какой будет вывод на консоль?
   *
   * Ответ:
   * 4
   *
   * Объяснение:
   * Дженерики существуют только во время компиляции. В рантайме происходит
   * стирание типа. JVM не может проверить тип того, что добавлено в список.
   */
  @Test
  public void quest10() {
    List longs = new ArrayList<Long>();
    longs.add(1L);
    longs.add(1.0);
    longs.add(new Object());
    longs.add("I am LONG!!");

    assertEquals(4, longs.size());
  }

  /**
   * Вопрос:
   * Какой будет результат выполнения кода?
   *
   * Ответ:
   * NoClassDefFoundError
   *
   * Объяснение:
   * 1. В static блоке выбрасывается NullPointerException.
   * 2. Блок catch перехватывает NPE так как NPE приводится к Throwable.
   * 3. Если в статическом инициализаторе класса произошёл выброс исключения,
   * то при любом новом обращии к классу JVM всегда будет выбрасывать NoClassDefFoundError,
   * потому что JVM не смогла загрузить данный класс.
   */
  @Test
  public void quest11() {
    Throwable exception = assertThrows(NoClassDefFoundError.class, () -> {
      try {
        Quest11.run();
      } catch (Throwable ex) {
        Quest11.run();
      }
    });
    assertEquals("java.lang.NoClassDefFoundError", exception.getClass().getName());
  }

  /**
   * Вопрос:
   * Какие исключения можно декларировать в сигнатуре имплеемнтации метода?
   * public void copy() throws ???
   *
   * Ответ:
   * 1. throws можно не декларировать вообще.
   * 2. FileNotFoundException, IllegalArgumentException.
   *
   * Объяснение:
   * Правило имплементации гласит, что список исключений нелья засширять.
   * При множественно инплементации интерфейсов в список исключений можно
   * добавлять только те исключения, которые могут выкинуть оба интерфейса:
   * FileNotFoundException расширяет IOException.
   * IllegalArgumentException расширяет RuntimeException. Его можно объявлять, а можно опустить.
   */
  interface I1 {
    void copy() throws IOError, IllegalArgumentException;
  }
  interface I2 {
    void copy() throws FileNotFoundException, InterruptedException;
  }
  class Quest12 implements I1, I2 {
    @Override
    public void copy() {
    }
  }

  /**
   * Вопрос:
   * Какой будет вывод на консоль?
   *
   * Ответ:
   * 123staticA
   *
   * Объяснение:
   * У перечислений в отличии от классов статический инициализатор выполняется
   * после конструктора.
   * 1. Ображение к Values.A вызывает инициализацию перечисления.
   * 2. Происходит инициализация каждого элемента перечисления.
   * Если у элемента есть конструктор, то вызывается конструктор.
   * 3. Выполняется статический инициализатор.
   * 4. Выводится имя элемента A.
   */
  @Test
  public void quest13() {
    System.out.println(Values.A);
  }
  enum Values {
    A(1), B(2), C(3);

    Values(int i) { System.out.print(i); }

    static { System.out.print("static"); }
  }
}

class Quest11 {
  static {
    if (true) throw new NullPointerException();
  }
  public static void run() {}
}
