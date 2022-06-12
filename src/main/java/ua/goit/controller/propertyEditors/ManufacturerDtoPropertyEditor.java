package ua.goit.controller.propertyEditors;

import ua.goit.model.dto.ManufacturerDto;

import java.beans.PropertyEditorSupport;
import java.util.Objects;
import java.util.UUID;

public class ManufacturerDtoPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (Objects.isNull(text) || text.isBlank()) {
            setValue(null);
            return;
        }
        final String[] split = text.split(",");
        ManufacturerDto dto = new ManufacturerDto();
        dto.setId(UUID.fromString(split[0].trim()));
        dto.setName(split[1].trim());
        setValue(dto);
    }
}
