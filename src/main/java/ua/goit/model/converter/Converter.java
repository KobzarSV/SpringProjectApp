package ua.goit.model.converter;

public interface Converter <T, E> {

    E toDto(T dao);

    T toDao(E dto);

}
