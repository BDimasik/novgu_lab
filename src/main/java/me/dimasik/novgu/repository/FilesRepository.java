package me.dimasik.novgu.repository;

import me.dimasik.novgu.entity.FileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends CrudRepository<FileEntity, Long> {
}
