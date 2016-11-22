package pl.lodz.p.iis.ppkwu.reddit.backend.utils;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class ImmutableListBuilderTest {

	@Test
	public void domyslnieBudowanaPustaLista() {
		ImmutableListBuilder<Integer> builder = new ImmutableListBuilder<>();
		List<Integer> list = builder.build();
		assertTrue(list.isEmpty());
	}

	@Test
	public void wynikowaListaZawieraDodaneElementy() {
		ImmutableListBuilder<Integer> builder = builderWithInts0to4();
		List<Integer> list = builder.build();
		assertThat(list, contains(0, 1, 2, 3, 4));
	}

	@Test
	public void modyfikacjaZrodlowejListyNieZmieniaWyniku() {
		ImmutableListBuilder<Integer> builder = builderWithInts0to4();
		List<Integer> list = builder.build();
		for (int i = 10; i < 15; ++i) {
			builder.addEntry(i);
		}
		assertThat(list, contains(0, 1, 2, 3, 4));
		builder.editableList().remove(2);
		assertThat(list, contains(0, 1, 2, 3, 4));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void wynikowaListaJestNiemodyfikowalna() {
		ImmutableListBuilder<Integer> builder = builderWithInts0to4();
		List<Integer> list = builder.build();
		list.set(0, 1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void pustaWynikowaListaJestNiemodyfikowalna() {
		List<Integer> list = new ImmutableListBuilder<Integer>().build();
		list.add(42);
	}

	private ImmutableListBuilder<Integer> builderWithInts0to4() {
		ImmutableListBuilder<Integer> builder = new ImmutableListBuilder<>();
		for (int i = 0; i < 5; ++i) {
			builder.addEntry(i);
		}
		return builder;
	}

}
