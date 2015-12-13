SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `library` ;
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`translations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`translations` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `en` VARCHAR(1000) NOT NULL,
  `ru` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`authors` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` INT(11) NOT NULL,
  `description` INT(11) NOT NULL,
  `image` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `name` (`name` ASC),
  INDEX `description` (`description` ASC),
  CONSTRAINT `authors_ibfk_1`
    FOREIGN KEY (`name`)
    REFERENCES `library`.`translations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `authors_ibfk_2`
    FOREIGN KEY (`description`)
    REFERENCES `library`.`translations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`publishers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`publishers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `title` (`title` ASC),
  CONSTRAINT `publishers_ibfk_1`
    FOREIGN KEY (`title`)
    REFERENCES `library`.`translations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`books`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`books` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` INT(11) NOT NULL,
  `description` INT(11) NULL DEFAULT NULL,
  `amount` INT(11) NOT NULL,
  `inStock` INT(11) NOT NULL,
  `publisherId` INT(11) NOT NULL,
  `year` INT(11) NOT NULL,
  `pages` INT(11) NOT NULL,
  `image` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `title` (`title` ASC),
  INDEX `description` (`description` ASC),
  INDEX `publisherId` (`publisherId` ASC),
  CONSTRAINT `books_ibfk_1`
    FOREIGN KEY (`title`)
    REFERENCES `library`.`translations` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `books_ibfk_2`
    FOREIGN KEY (`description`)
    REFERENCES `library`.`translations` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `books_ibfk_3`
    FOREIGN KEY (`publisherId`)
    REFERENCES `library`.`publishers` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`books_authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`books_authors` (
  `bookId` INT(11) NOT NULL,
  `authorId` INT(11) NOT NULL,
  PRIMARY KEY (`bookId`, `authorId`),
  INDEX `authorId` (`authorId` ASC),
  CONSTRAINT `books_authors_ibfk_1`
    FOREIGN KEY (`bookId`)
    REFERENCES `library`.`books` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `books_authors_ibfk_2`
    FOREIGN KEY (`authorId`)
    REFERENCES `library`.`authors` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(100) NOT NULL,
  `lastName` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `login` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` TINYINT(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC),
  UNIQUE INDEX `login` (`login` ASC),
  INDEX `login_2` (`login` ASC),
  INDEX `email_2` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`subscriptions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`subscriptions` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `userId` INT(11) NOT NULL,
  `expirationDate` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `userId` (`userId` ASC),
  CONSTRAINT `subscriptions_ibfk_1`
    FOREIGN KEY (`userId`)
    REFERENCES `library`.`users` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `library`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` TINYINT(4) NOT NULL DEFAULT '0',
  `subscriptionId` INT(11) NOT NULL,
  `bookId` INT(11) NOT NULL,
  `dueDate` DATETIME NOT NULL,
  `fee` FLOAT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `subscriptionId` (`subscriptionId` ASC),
  INDEX `bookId` (`bookId` ASC),
  CONSTRAINT `orders_ibfk_1`
    FOREIGN KEY (`subscriptionId`)
    REFERENCES `library`.`subscriptions` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `orders_ibfk_2`
    FOREIGN KEY (`bookId`)
    REFERENCES `library`.`books` (`id`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;

USE `library` ;

-- -----------------------------------------------------
-- Placeholder table for view `library`.`authorsview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`authorsview` (`author_id` INT, `author_image` INT, `author_name_id` INT, `author_name_en` INT, `author_name_ru` INT, `author_description_id` INT, `author_description_en` INT, `author_description_ru` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`booksview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`booksview` (`book_id` INT, `book_amount` INT, `book_inStock` INT, `book_year` INT, `book_pages` INT, `book_image` INT, `book_title_id` INT, `book_title_en` INT, `book_title_ru` INT, `book_description_id` INT, `book_description_en` INT, `book_description_ru` INT, `publisher_id` INT, `publisher_title_id` INT, `publisher_title_en` INT, `publisher_title_ru` INT, `author_id` INT, `author_image` INT, `author_name_id` INT, `author_name_en` INT, `author_name_ru` INT, `author_description_id` INT, `author_description_en` INT, `author_description_ru` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`detailedorders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`detailedorders` (`order_id` INT, `order_type` INT, `order_subscriptionId` INT, `order_bookId` INT, `order_dueDate` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`feesumview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`feesumview` (`feeSum_id` INT, `feeSum` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`ordersview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`ordersview` (`id` INT, `type` INT, `subscriptionId` INT, `bookId` INT, `dueDate` INT, `fee` INT, `book_id` INT, `book_amount` INT, `book_inStock` INT, `book_year` INT, `book_pages` INT, `book_image` INT, `book_title_id` INT, `book_title_en` INT, `book_title_ru` INT, `book_description_id` INT, `book_description_en` INT, `book_description_ru` INT, `publisher_id` INT, `publisher_title_id` INT, `publisher_title_en` INT, `publisher_title_ru` INT, `author_id` INT, `author_image` INT, `author_name_id` INT, `author_name_en` INT, `author_name_ru` INT, `author_description_id` INT, `author_description_en` INT, `author_description_ru` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`publishersview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`publishersview` (`publisher_id` INT, `publisher_title_id` INT, `publisher_title_en` INT, `publisher_title_ru` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`readersview`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`readersview` (`id` INT, `firstName` INT, `lastName` INT, `email` INT, `login` INT, `password` INT, `role` INT, `subscription_id` INT, `subscription_userId` INT, `subscription_expirationDate` INT);

-- -----------------------------------------------------
-- Placeholder table for view `library`.`userbooks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `library`.`userbooks` (`id` INT, `firstName` INT, `lastName` INT, `email` INT, `login` INT, `password` INT, `role` INT, `subscription_id` INT, `subscription_userId` INT, `subscription_expirationDate` INT, `feeSum` INT, `book_id` INT, `book_amount` INT, `book_inStock` INT, `book_year` INT, `book_pages` INT, `book_image` INT, `book_title_id` INT, `book_title_en` INT, `book_title_ru` INT, `book_description_id` INT, `book_description_en` INT, `book_description_ru` INT, `publisher_id` INT, `publisher_title_id` INT, `publisher_title_en` INT, `publisher_title_ru` INT, `author_id` INT, `author_image` INT, `author_name_id` INT, `author_name_en` INT, `author_name_ru` INT, `author_description_id` INT, `author_description_en` INT, `author_description_ru` INT);

-- -----------------------------------------------------
-- View `library`.`authorsview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`authorsview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`authorsview` AS select `library`.`authors`.`id` AS `author_id`,`library`.`authors`.`image` AS `author_image`,`author_name`.`id` AS `author_name_id`,`author_name`.`en` AS `author_name_en`,`author_name`.`ru` AS `author_name_ru`,`author_description`.`id` AS `author_description_id`,`author_description`.`en` AS `author_description_en`,`author_description`.`ru` AS `author_description_ru` from ((`library`.`authors` join `library`.`translations` `author_name` on((`author_name`.`id` = `library`.`authors`.`name`))) left join `library`.`translations` `author_description` on((`author_description`.`id` = `library`.`authors`.`description`)));

-- -----------------------------------------------------
-- View `library`.`booksview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`booksview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`booksview` AS select `library`.`books`.`id` AS `book_id`,`library`.`books`.`amount` AS `book_amount`,`library`.`books`.`inStock` AS `book_inStock`,`library`.`books`.`year` AS `book_year`,`library`.`books`.`pages` AS `book_pages`,`library`.`books`.`image` AS `book_image`,`book_title`.`id` AS `book_title_id`,`book_title`.`en` AS `book_title_en`,`book_title`.`ru` AS `book_title_ru`,`book_description`.`id` AS `book_description_id`,`book_description`.`en` AS `book_description_en`,`book_description`.`ru` AS `book_description_ru`,`publishersview`.`publisher_id` AS `publisher_id`,`publishersview`.`publisher_title_id` AS `publisher_title_id`,`publishersview`.`publisher_title_en` AS `publisher_title_en`,`publishersview`.`publisher_title_ru` AS `publisher_title_ru`,`authorsview`.`author_id` AS `author_id`,`authorsview`.`author_image` AS `author_image`,`authorsview`.`author_name_id` AS `author_name_id`,`authorsview`.`author_name_en` AS `author_name_en`,`authorsview`.`author_name_ru` AS `author_name_ru`,`authorsview`.`author_description_id` AS `author_description_id`,`authorsview`.`author_description_en` AS `author_description_en`,`authorsview`.`author_description_ru` AS `author_description_ru` from (((((`library`.`books` left join `library`.`translations` `book_title` on((`book_title`.`id` = `library`.`books`.`title`))) left join `library`.`books_authors` on((`library`.`books_authors`.`bookId` = `library`.`books`.`id`))) left join `library`.`translations` `book_description` on((`book_description`.`id` = `library`.`books`.`description`))) left join `library`.`publishersview` on((`publishersview`.`publisher_id` = `library`.`books`.`publisherId`))) left join `library`.`authorsview` on((`authorsview`.`author_id` = `library`.`books_authors`.`authorId`))) order by `library`.`books`.`id`;

-- -----------------------------------------------------
-- View `library`.`detailedorders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`detailedorders`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`detailedorders` AS select `library`.`orders`.`id` AS `order_id`,`library`.`orders`.`type` AS `order_type`,`library`.`orders`.`subscriptionId` AS `order_subscriptionId`,`library`.`orders`.`bookId` AS `order_bookId`,`library`.`orders`.`dueDate` AS `order_dueDate` from `library`.`orders`;

-- -----------------------------------------------------
-- View `library`.`feesumview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`feesumview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`feesumview` AS select `library`.`orders`.`id` AS `feeSum_id`,sum(`library`.`orders`.`fee`) AS `feeSum` from `library`.`orders` group by `library`.`orders`.`subscriptionId`;

-- -----------------------------------------------------
-- View `library`.`ordersview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`ordersview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`ordersview` AS select `library`.`orders`.`id` AS `id`,`library`.`orders`.`type` AS `type`,`library`.`orders`.`subscriptionId` AS `subscriptionId`,`library`.`orders`.`bookId` AS `bookId`,`library`.`orders`.`dueDate` AS `dueDate`,`library`.`orders`.`fee` AS `fee`,`booksview`.`book_id` AS `book_id`,`booksview`.`book_amount` AS `book_amount`,`booksview`.`book_inStock` AS `book_inStock`,`booksview`.`book_year` AS `book_year`,`booksview`.`book_pages` AS `book_pages`,`booksview`.`book_image` AS `book_image`,`booksview`.`book_title_id` AS `book_title_id`,`booksview`.`book_title_en` AS `book_title_en`,`booksview`.`book_title_ru` AS `book_title_ru`,`booksview`.`book_description_id` AS `book_description_id`,`booksview`.`book_description_en` AS `book_description_en`,`booksview`.`book_description_ru` AS `book_description_ru`,`booksview`.`publisher_id` AS `publisher_id`,`booksview`.`publisher_title_id` AS `publisher_title_id`,`booksview`.`publisher_title_en` AS `publisher_title_en`,`booksview`.`publisher_title_ru` AS `publisher_title_ru`,`booksview`.`author_id` AS `author_id`,`booksview`.`author_image` AS `author_image`,`booksview`.`author_name_id` AS `author_name_id`,`booksview`.`author_name_en` AS `author_name_en`,`booksview`.`author_name_ru` AS `author_name_ru`,`booksview`.`author_description_id` AS `author_description_id`,`booksview`.`author_description_en` AS `author_description_en`,`booksview`.`author_description_ru` AS `author_description_ru` from (`library`.`orders` join `library`.`booksview` on((`booksview`.`book_id` = `library`.`orders`.`bookId`)));

-- -----------------------------------------------------
-- View `library`.`publishersview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`publishersview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`publishersview` AS select `library`.`publishers`.`id` AS `publisher_id`,`publisher_title`.`id` AS `publisher_title_id`,`publisher_title`.`en` AS `publisher_title_en`,`publisher_title`.`ru` AS `publisher_title_ru` from (`library`.`publishers` join `library`.`translations` `publisher_title` on((`publisher_title`.`id` = `library`.`publishers`.`title`)));

-- -----------------------------------------------------
-- View `library`.`readersview`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`readersview`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`readersview` AS select `library`.`users`.`id` AS `id`,`library`.`users`.`firstName` AS `firstName`,`library`.`users`.`lastName` AS `lastName`,`library`.`users`.`email` AS `email`,`library`.`users`.`login` AS `login`,`library`.`users`.`password` AS `password`,`library`.`users`.`role` AS `role`,`library`.`subscriptions`.`id` AS `subscription_id`,`library`.`subscriptions`.`userId` AS `subscription_userId`,`library`.`subscriptions`.`expirationDate` AS `subscription_expirationDate` from (`library`.`users` join `library`.`subscriptions` on((`library`.`subscriptions`.`userId` = `library`.`users`.`id`)));

-- -----------------------------------------------------
-- View `library`.`userbooks`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`userbooks`;
USE `library`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `library`.`userbooks` AS select `readersview`.`id` AS `id`,`readersview`.`firstName` AS `firstName`,`readersview`.`lastName` AS `lastName`,`readersview`.`email` AS `email`,`readersview`.`login` AS `login`,`readersview`.`password` AS `password`,`readersview`.`role` AS `role`,`readersview`.`subscription_id` AS `subscription_id`,`readersview`.`subscription_userId` AS `subscription_userId`,`readersview`.`subscription_expirationDate` AS `subscription_expirationDate`,`feesumview`.`feeSum` AS `feeSum`,`booksview`.`book_id` AS `book_id`,`booksview`.`book_amount` AS `book_amount`,`booksview`.`book_inStock` AS `book_inStock`,`booksview`.`book_year` AS `book_year`,`booksview`.`book_pages` AS `book_pages`,`booksview`.`book_image` AS `book_image`,`booksview`.`book_title_id` AS `book_title_id`,`booksview`.`book_title_en` AS `book_title_en`,`booksview`.`book_title_ru` AS `book_title_ru`,`booksview`.`book_description_id` AS `book_description_id`,`booksview`.`book_description_en` AS `book_description_en`,`booksview`.`book_description_ru` AS `book_description_ru`,`booksview`.`publisher_id` AS `publisher_id`,`booksview`.`publisher_title_id` AS `publisher_title_id`,`booksview`.`publisher_title_en` AS `publisher_title_en`,`booksview`.`publisher_title_ru` AS `publisher_title_ru`,`booksview`.`author_id` AS `author_id`,`booksview`.`author_image` AS `author_image`,`booksview`.`author_name_id` AS `author_name_id`,`booksview`.`author_name_en` AS `author_name_en`,`booksview`.`author_name_ru` AS `author_name_ru`,`booksview`.`author_description_id` AS `author_description_id`,`booksview`.`author_description_en` AS `author_description_en`,`booksview`.`author_description_ru` AS `author_description_ru` from (((`library`.`readersview` join `library`.`detailedorders` on((`detailedorders`.`order_subscriptionId` = `readersview`.`subscription_id`))) join `library`.`feesumview` on((`feesumview`.`feeSum_id` = `detailedorders`.`order_id`))) left join `library`.`booksview` on((`booksview`.`book_id` = `detailedorders`.`order_bookId`))) where (`feesumview`.`feeSum` > 0) order by `readersview`.`id`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
USE `library`;

DELIMITER $$
USE `library`$$
CREATE
TRIGGER `library`.`authors_after_delete`
AFTER DELETE ON `library`.`authors`
FOR EACH ROW
BEGIN
    DELETE FROM translations WHERE id = old.name;
    DELETE FROM translations WHERE id = old.description;
    DELETE b FROM books b
      JOIN books_authors ba ON b.id = ba.bookId WHERE ba.authorId = old.id;
  END$$

USE `library`$$
CREATE
TRIGGER `library`.`publishers_after_delete`
AFTER DELETE ON `library`.`publishers`
FOR EACH ROW
BEGIN
    DELETE FROM translations WHERE id = old.title;
    DELETE FROM books WHERE publisherId = old.id;
  END$$

USE `library`$$
CREATE
TRIGGER `library`.`books_after_delete`
AFTER DELETE ON `library`.`books`
FOR EACH ROW
BEGIN
    DELETE FROM translations WHERE id = old.title;
    DELETE FROM translations WHERE id = old.description;
  END$$


DELIMITER ;


#insertauthorsauthors
INSERT INTO `translations` (`id`, `en`, `ru`) VALUES
  (1, 'Orson Scott Card', 'Орсон Скотт Кард'),
  (2, 'Ender''s Game', 'Игра Эндера'),
  (3, 'Terrestrial civilization at stake. For seven decades, humanity is lose-lose war with alien alien race, and a chance to win all melt. Really there is no hope and humanity will perish? And this hope appears. On earth is born a genius, a child is destined to become the savior of mankind. His name is Andrew Wiggin, Ender, or what it means to the winner. "Ender''s Game" - an absolute masterpiece of modern fiction and a rare case in the history of the genre, when the novel won a one-year top two fantastic awards - award "Hugo" and "Nebula". That is, at the same time and gets the reader''s and writer''s confession.', 'Земная цивилизация под угрозой. Уже семь десятилетий человечество ведет безвыигрышную войну с чуждой инопланетной расой, и шансы на победу всё тают. Неужели нет никакой надежды и человечеству придется погибнуть? И такая надежда появляется. На Земле рождается гений, ребенок, которому суждено стать спасителем человечества. Имя его Эндрю Виггин, или Эндер, что значит победитель. «Игра Эндера» — абсолютный шедевр современной фантастики и редкий случай в истории жанра, когда роман завоевывает в один год две высшие фантастические награды — премии «Хьюго» и «Небьюла». То есть получает одновременно и читательское, и писательское признание.'),
  (4, 'An American novelist, critic, public speaker, essayist and columnist. He writes in several genres but is known best for science fiction', 'Американский писатель-фантаст, автор ряда книг в жанрах научной фантастики, фэнтези и альтернативной истории'),
  (5, 'Ray Bradbury', 'Рэй Брэдбери'),
  (6, 'American writer, known for his dystopian "Fahrenheit 451", a cycle of short stories "The Martian Chronicles" and partly autobiographical novel "Dandelion Wine"', 'Американский писатель, известный по антиутопии «451 градус по Фаренгейту», циклу рассказов «Марсианские хроники» и частично автобиографическому роману «Вино из одуванчиков»'),
  (7, 'Azbuka-Atticus', 'Азбука-Аттикус'),
  (8, 'Rimis', 'Римис'),
  (9, 'Fahrenheit 451', '451 градус по Фаренгейту'),
  (10, 'Fahrenheit 451 is a dystopian novel by Ray Bradbury published in 1953. It is regarded as one of his best works.The novel presents a future American society where books are outlawed and "firemen" burn any that are found.', '451 градус по Фаренгейту является романом-антиутопией Рэя Брэдбери. Опубликован в 1953 году, считается одной из его лучших работ. Роман описывает американское общество будущего, где книги объявлены вне закона и "пожарные" сжигает все книги, которые могут найти.'),
  (18, 'Eksmo', 'Эксмо'),
  (19, 'The Martian Chronicles', 'Марсианские хроники'),
  (20, 'The Martian Chronicles is a science fiction short story collection by Ray Bradbury that chronicles the colonization of Mars by humans fleeing from a troubled and eventually devastated Earth, and the conflict between aboriginal Martians and the new colonists.', 'Марсианские Хроники  - это сборник фантастических рассказов рассказов Рэя Брэдбери, который является летописью колонизации Марса людьми, спасающихся от погрязжей в проблемах и в конечном итоге разрушенной Земле, и конфликт между коренными марсианами и новыми колонистами.'),
  (21, 'Henry Lion Oldie', 'Генри Лайон Олди'),
  (22, 'Henry Lion Oldie is the pen name of Ukrainian science fiction writers Dmitry Gromov and Oleg Ladyzhensky. Both authors reside in Kharkiv, Ukraine, and write in Russian. At Eurocon 2006 in Kyiv, the European Science Fiction Society named them Europe''s best writers of 2006.', 'Генри Лайон Олди является псевдонимом украинских писателей-фантастов Дмитрия Громова и Олега Ладыженского. Оба автора проживают в Харькове, Украине и пишут на русском. На европейском конвенте фантастов «Eurocon-2006» Г. Л. Олди был признан лучшим писателем-фантастом Европы 2006 года.'),
  (23, 'The Hero Must Be Alone', 'Герой должен быть один'),
  (24, 'The myth of the labors of Hercules is known to all from childhood. But not everyone knows that a young Hercules crossed the interests of the Olympic Family, deposed in Tartarus Titans, as well as a lot of people, resulting in future hero and his brother Iphicles from childhood became hostages of foreign intrigue. And of course, no one had heard of the sinister attacks of insanity, which is subject to the Great Hercules on altars Possessed Tartarus, where steaming blood of human victims, and a deadly secret that the earthly father of Hercules - Amphitryon, a grandson of Perseus - forced to hold up to his death and even after it ...', 'Миф о подвигах Геракла известен всем с малолетства. Но не все знают, что на юном Геракле пересеклись интересы Олимпийской Семьи, свергнутых в Тартар титанов, а также многих людей, в результате чего будущий герой и его брат Ификл с детства стали заложниками чужих интриг. И уж конечно, никто не слышал о зловещих приступах безумия, которым подвержен Великий Геракл, об алтарях Одержимых Тартаром, на которых дымится кровь человеческих жертв, и о смертельно опасной тайне, которую земной отец Геракла – Амфитрион, внук Персея – вынужден хранить до самой смерти и даже после нее…'),
  (25, 'Andrey Valentinov', 'Андрей Валентинов'),
  (26, 'Science fiction writer. Winner of several awards in the genre of science fiction. Historian by education (Kharkov University, 1980), Ph.D., associate professor of Kharkiv National University.', 'Писатель-фантаст. Лауреат ряда премий в жанре фантастики. По образованию историк (Харьковский университет, 1980), кандидат исторических наук, доцент Харьковского национального университета.'),
  (27, 'Armageddon was yesterday', 'Армагеддон был вчера'),
  (28, 'The action takes place in the 20 years of this century, in a world where there have been several local man-made apocalypse, "and no one understands that this is only the beginning."', 'Действие происходит в 20-е годы нынешнего века, в мире, где произошли несколько рукотворных локальных апокалипсисов, «и никто не понимает, что это только начало».'),
  (29, 'Isaac Asimov', 'Айзек Азимов'),
  (30, 'American science fiction writer and popularizer of science, a biochemist. Author of over 500 books, mostly art (especially in the genre of science fiction, but also in other genres: fantasy, detective, humor) and popular science (in a variety of fields - from astronomy to genetics and history and literature). Multiple winner of the Hugo and Nebula awards.', 'Американский писатель-фантаст, популяризатор науки, биохимик. Автор около 500 книг, в основном художественных (прежде всего в жанре научной фантастики, но также и в других жанрах: фэнтези, детектив, юмор) и научно-популярных (в самых разных областях — от астрономии и генетики до истории и литературоведения). Многократный лауреат премий Хьюго и Небьюла.'),
  (31, 'The End of Eternity', 'Конец вечности'),
  (32, 'A science fiction novel, with mystery and thriller elements, on the subjects of time travel and social engineering. The themes are very different from most of his robot and ''space opera'' stories, and take a clever approach to time paradoxes.', 'Научно-фантастический роман, с тайной и триллера элементов, про путешествия во времени и социальной инженерии. Темы очень отличаются от большинства работ автора про роботов и «космическую оперу» и умело описывают временные парадоксы.'),
  (33, 'John Ronald Reuel Tolkien', 'Джон Рональд Руэл Толкин'),
  (34, 'An English writer, poet, philologist, and university professor, best known as the author of the classic high-fantasy works The Hobbit, The Lord of the Rings, and The Silmarillion.', 'Английский писатель, поэт, филолог, профессор Оксфордского университета. Наиболее известен как автор классических произведений «высокого фэнтези»: «Хоббит, или Туда и обратно», «Властелин колец» и «Сильмариллион».'),
  (35, 'The Lord of the Rings: The Fellowship of the Ring', 'Властелин колец: Братство Кольца'),
  (36, 'Frodo the hobbit, Bilbo Baggins famous nephew, trust is important and very dangerous mission - to keep the One Ring, which must be destroyed in the flames Flaming Mountains, as if it would not be destroyed with the help of the Dark Lord Sauron able to subdue all peoples of Middle-earth. And the brave hobbit friends goes to the full deadly journey ...', 'Хоббиту Фродо, племяннику знаменитого Бильбо Бэггинса, доверена важная и очень опасная миссия — хранить Кольцо Всевластья, которое нужно уничтожить в горниле Огненной Горы, так как, если оно не будет уничтожено, с его помощью Темный Властелин Саурон сможет подчинить себе все народы Средиземья. И отважный хоббит с друзьями отправляется в полное смертельных опасностей путешествие…'),
  (37, 'AST', 'АСТ'),
  (38, 'The Lord of the Rings: The Two Towers', 'Властелин колец: Две крепости'),
  (39, 'Brotherhood broke, but Ring must be destroyed. Frodo and Sam are forced to entrust their lives to Gollum, who undertook to carry them to the gates of Mordor. A huge army of Saruman approaches: Brotherhood members and their allies are ready to fight. Battle for Middle Earth continues.', 'Братство распалось, но Кольцо Всевластья должно быть уничтожено. Фродо и Сэм вынуждены доверить свои жизни Голлуму, который взялся провести их к вратам Мордора. Громадная Армия Сарумана приближается: члены братства и их союзники готовы принять бой. Битва за Средиземье продолжается.'),
  (40, 'The Lord of the Rings: The Return of the King', 'Властелин колец: Возвращение короля'),
  (41, 'The last part of the trilogy about the One Ring and the heroes who have taken upon themselves the burden of saving Middle Earth. Lord of the forces of Darkness Sauron sends his countless armies of the walls of Minas Tirith, the fortress of Hope. He looks forward to a close victory, but that''s what prevents him notice two tiny figures - Hobbits, approaching Mount Doom, where they have to destroy the One Ring. Do they smile happiness?', 'Последняя часть трилогии о Кольце Всевластия и о героях, взявших на себя бремя спасения Средиземья. Повелитель сил Тьмы Саурон направляет свои бесчисленные рати под стены Минас-Тирита, крепости Последней Надежды. Он предвкушает близкую победу, но именно это и мешает ему заметить две крохотные фигурки — хоббитов, приближающихся к Роковой Горе, где им предстоит уничтожить Кольцо Всевластия. Улыбнется ли им счастье?');


INSERT INTO `users` (`id`, `firstName`, `lastName`, `email`, `login`, `password`, `role`) VALUES
  (1, 'Владислав', 'Лысяк', 'camael1290@gmail.com', 'admin', 'admin', 5),
  (2, 'Владислав', 'Лысяк', 'sample@mail.ru', 'Vladislav', 'userpass', 3),
  (3, 'Александр', 'Домогаров', 'sample2@mail.ru', 'Sasha', 'userpass', 3),
  (4, 'Дмитрий', 'Nagiev', 'sample3@mail.ru', 'Dima', 'userpass', 3),
  (5, 'Aleksey', 'Ivanoff', 'sample4@rambler.ru', 'Lesha', 'userpass', 3),
  (6, 'Jason', 'Statham', 'sample5@mail.ua', 'Jenia', 'userpass', 3),
  (7, 'Natalia', 'Lavrova', 'tashakh2@mail.ru', 'Natasha', 'userpass', 3),
  (8, 'Andrey', 'Tregubov', 'sample6@mail.en', 'Librarian', 'librarian', 4),
  (9, 'Lina', 'Topolina', 'email@mail.com', 'Login', 'overdue', 2),
  (10, 'Samir', 'Gadzhaliev', 'samir@most.com', 'SAmir', 'banned', 1),
  (11, 'Mikhail', 'Golubev', 'golub@gmail.com', 'Misha', 'confirmation', 0);

INSERT INTO `subscriptions` (`id`, `userId`, `expirationDate`) VALUES
  (1, 7, '2016-01-12 00:00:00'),
  (2, 6, '2014-02-02 00:00:00'),
  (3, 5, '2016-01-12 00:00:00'),
  (4, 2, '2016-02-02 00:00:00'),
  (5, 3, '2016-02-02 00:00:00'),
  (6, 9, '2016-02-02 00:00:00'),
  (7, 4, '2016-02-02 00:00:00'),
  (8, 10, '2016-02-02 00:00:00');

INSERT INTO `publishers` (`id`, `title`) VALUES
  (1, 7),
  (2, 8),
  (4, 18),
  (5, 37);

INSERT INTO `authors` (`id`, `name`, `description`, `image`) VALUES
  (1, 1, 4, '1.jpg'),
  (2, 5, 6, '2.jpg'),
  (3, 21, 22, '3.jpg'),
  (4, 25, 26, '4.jpg'),
  (5, 29, 30, '5.jpg'),
  (6, 33, 34, '6.jpg');


INSERT INTO `books` (`id`, `title`, `description`, `amount`, `inStock`, `publisherId`, `year`, `pages`, `image`) VALUES
  (1, 2, 3, 2, 0, 1, 2013, 384, '1.jpg'),
  (2, 9, 10, 10, 4, 2, 2011, 304, '2.jpg'),
  (6, 19, 20, 5, 5, 4, 2008, 368, '6.jpg'),
  (7, 23, 24, 7, 6, 1, 2014, 576, '7.jpg'),
  (8, 27, 28, 8, 8, 4, 1999, 372, '8.jpg'),
  (9, 31, 32, 12, 12, 1, 2000, 336, '9.jpg'),
  (10, 35, 36, 4, 4, 5, 2014, 576, '10.jpg'),
  (11, 38, 39, 7, 7, 5, 2014, 480, '11.jpg'),
  (12, 40, 41, 8, 8, 5, 2014, 448, '12.jpg');


INSERT INTO `books_authors` (`bookId`, `authorId`) VALUES
  (1, 1),
  (2, 2),
  (6, 2),
  (7, 3),
  (8, 3),
  (8, 4),
  (9, 5),
  (10, 6),
  (11, 6),
  (12, 6);

INSERT INTO `orders` (`id`, `type`, `subscriptionId`, `bookId`, `dueDate`, `fee`) VALUES
  (1, 2, 6, 1, '2015-02-14 00:00:00', 0),
  (2, 1, 6, 1, '2015-03-18 00:00:00', 0),
  (3, 1, 6, 2, '2015-05-01 00:00:00', 0),
  (4, 0, 6, 2, '2015-02-05 00:00:00', 0),
  (5, 0, 6, 2, '2015-02-05 00:00:00', 0),
  (6, 0, 6, 2, '2015-02-05 00:00:00', 0),
  (7, 0, 6, 1, '2015-02-02 00:00:00', 10),
  (8, 2, 1, 1, '2015-02-02 00:00:00', 0),
  (9, 0, 3, 1, '2015-02-02 00:00:00', 10),
  (10, 0, 1, 2, '2015-02-08 00:00:00', 0),
  (11, 0, 1, 2, '2015-02-08 00:00:00', 0),
  (12, 0, 1, 7, '2015-02-08 00:00:00', 0);
