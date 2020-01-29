package multiscripter.tmp.models;

import java.util.Objects;

/**
 * Реализует сущность Пользователь.
 */
public class User {

  /**
   * Возраст.
   */
  private int age;

  /**
   * Имя.
   */
  private String name;

  /**
   * Конструктор по умолчанию.
   */
  public User() {
  }

  /**
   * Конструктор.
   *
   * @param name имя.
   * @param age возраст.
   */
  public User(final String name, final int age) {
    this.age = age;
    this.name = name;
  }

  /**
   * Проверяет на равенство указанный объект и текущий объект.
   *
   * @param o указанный объект.
   * @return true если объекты равны. Иначе false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    User that = (User) o;
    return this.age == that.getAge()
        && Objects.equals(this.name, that.getName());
  }

  /**
   * Вычисляет хэш-сумму объекта.
   *
   * @return хэш-сумма объекта.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getAge(), getName());
  }

  /**
   * Получает возраст.
   *
   * @return возраст.
   */
  public int getAge() {
    return this.age;
  }

  /**
   * Устанавливает возраст.
   *
   * @param age возраст.
   */
  public void setAge(final int age) {
    this.age = age;
  }

  /**
   * Получает имя.
   *
   * @return имя.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Устанавливает имя.
   *
   * @param name имя.
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строковое представление объекта.
   */
  @Override
  public String toString() {
    return String.format("User{name: %s, age: %d}", this.name, this.age);
  }
}
