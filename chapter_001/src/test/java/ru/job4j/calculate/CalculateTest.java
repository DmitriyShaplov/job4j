package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author shaplov
 * @version $Id$
 * @since 01.12.2018
 */
public class CalculateTest {
	/**
	 * Test echo.
	 */
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "Dmitriy Shaplov";
		String expect = "Echo, echo, echo : Dmitriy Shaplov";
//Создание нового объекта.
		Calculate calc = new Calculate();
//Выполнение метода echo с параметром input и запись ее в переменную result.
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
}
