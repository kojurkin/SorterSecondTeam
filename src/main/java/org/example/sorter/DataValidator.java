package org.example.sorter;

import org.example.student.Student;
import org.jspecify.annotations.NonNull;

import java.util.Arrays;
import java.util.List;

class DataValidator {

    public boolean isNotNeedToSort(List<Student> studentList) {
        return studentList == null || studentList.size() < 2;
    }

    public DataValidator validateArrAndElementNotNull(@NonNull String errorPrefix
            , @NonNull Object[] array) {
        String elementErrorMessage = errorPrefix + "list can't be null";
        throwIfNull(array, errorPrefix + " element can't be null");
        Arrays.stream(array).forEach(
                clientFieldName -> throwIfNull(clientFieldName, elementErrorMessage)
        );
        return this;
    }

    public DataValidator throwIfValid(@NonNull IValidator validator, String exceptionMessage) {
        if (validator.isValid()) {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return this;
    }

    public DataValidator throwIfNull(Object object, String errorsMessage) {
        throwIfValid(() -> object == null, errorsMessage);
        return this;
    }
}
