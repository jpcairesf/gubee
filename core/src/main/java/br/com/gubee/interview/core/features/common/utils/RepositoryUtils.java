package br.com.gubee.interview.core.features.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.NONE)
public class RepositoryUtils {

    public static RowMapper<UUID> UUIDMapper() {
        return (resultSet, rowNum) -> (UUID) resultSet.getObject("id");
    }

}
