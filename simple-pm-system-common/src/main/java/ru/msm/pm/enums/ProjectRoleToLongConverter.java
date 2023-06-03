package ru.msm.pm.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

    @Converter
    public class ProjectRoleToLongConverter implements AttributeConverter<ProjectRole, Long> {

        @Override
        public Long convertToDatabaseColumn(ProjectRole attribute) {
            return (long) attribute.ordinal();
        }

        @Override
        public ProjectRole convertToEntityAttribute(Long dbData) {
            return ProjectRole.values()[Math.toIntExact(dbData)-1];
        }
    }


