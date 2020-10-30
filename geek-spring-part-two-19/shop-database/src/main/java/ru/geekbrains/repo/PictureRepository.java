package ru.geekbrains.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
