import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class Tets1 {
    @Test
    public void iterator_will_return_hello_world() {
//        Этот пример демонстрирует создание мок-итератора и «заставляет»
//        его возвращать «Hello» при первом вызове метода next().
//        Последующие вызовы этого метода будут возвращать «World».
//        После этого мы можем выполнять обычные assert'ы.
        //подготавливаем
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //выполняем
        String result = i.next() + " " + i.next();
        //сравниваем
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
//        Здесь мы создаём объект-заглушку Comparable, и возвращаем 1 в случае,
//        если он сравнивается с определённым String-значением («Test», в данном случае).
        Comparable c = mock(Comparable.class);
        when(c.compareTo("Test")).thenReturn(1);
        assertEquals(1, c.compareTo("Test"));
    }

    @Test
    public void with_unspecified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(5));
    }
}