-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Дек 07 2022 г., 12:11
-- Версия сервера: 8.0.30
-- Версия PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `BarberDB`
--

-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE `client` (
  `id` bigint NOT NULL COMMENT 'Уникальный идентификатор\r\n',
  `firstname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Фамилия клиента\r\n',
  `user_id` bigint NOT NULL COMMENT 'Внешний ключ к таблице user',
  `lastname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Имя клиента\r\n',
  `middlename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Отчество клиента',
  `phone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `client`
--

INSERT INTO `client` (`id`, `firstname`, `user_id`, `lastname`, `middlename`, `phone`) VALUES
(4, 'qwe', 16, 'qwe', 'qwe', '+78888888888');

-- --------------------------------------------------------

--
-- Структура таблицы `consumption`
--

CREATE TABLE `consumption` (
  `id` bigint NOT NULL,
  `dateconsumption` date DEFAULT NULL,
  `valueconsumption` double NOT NULL,
  `supply_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `consumption`
--

INSERT INTO `consumption` (`id`, `dateconsumption`, `valueconsumption`, `supply_id`) VALUES
(1, '2022-12-07', 12324, 19),
(2, '2022-12-08', 1111, 20),
(3, '2022-12-15', 45678, 21),
(4, '2022-12-22', 234567, 22);

-- --------------------------------------------------------

--
-- Структура таблицы `employer`
--

CREATE TABLE `employer` (
  `id` bigint NOT NULL,
  `firstname` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `inn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lastname` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `middlename` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `post_id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `employer`
--

INSERT INTO `employer` (`id`, `firstname`, `inn`, `lastname`, `middlename`, `post_id`, `user_id`) VALUES
(1, 'Новиков', '123456789000', 'Илья', 'Олегович', 1, 58),
(2, 'фывавыва', '6676787687', 'вывапа', 'чсмпмми', 1, 59);

-- --------------------------------------------------------

--
-- Структура таблицы `income`
--

CREATE TABLE `income` (
  `id` bigint NOT NULL,
  `dateincome` date DEFAULT NULL,
  `valueincome` double NOT NULL,
  `record_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `income`
--

INSERT INTO `income` (`id`, `dateincome`, `valueincome`, `record_id`) VALUES
(16, '2022-11-30', 1305, 4),
(17, '2022-12-16', 1305, 5),
(18, '2022-12-09', 1305, 6),
(19, '2022-12-25', 2175, 7);

-- --------------------------------------------------------

--
-- Структура таблицы `material`
--

CREATE TABLE `material` (
  `id` bigint NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `valuematerials` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `material`
--

INSERT INTO `material` (`id`, `name`, `valuematerials`) VALUES
(1, 'Ножницы', 15),
(2, 'Краска зеленая', 11),
(3, 'qwety', 1),
(4, 'qwe456u', 0),
(8, 'цук', 2345),
(9, 'арпимт', 234);

-- --------------------------------------------------------

--
-- Структура таблицы `material_supply`
--

CREATE TABLE `material_supply` (
  `supply_id` bigint NOT NULL,
  `material_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `material_supply`
--

INSERT INTO `material_supply` (`supply_id`, `material_id`) VALUES
(17, 1),
(17, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `post`
--

CREATE TABLE `post` (
  `id` bigint NOT NULL,
  `namepost` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `post`
--

INSERT INTO `post` (`id`, `namepost`, `price`) VALUES
(1, 'Администратор', 70000),
(2, 'Бухгалтер', 60000),
(3, 'Сотрудник отдела кадров', 63000),
(4, 'Кладовщик', 43000);

-- --------------------------------------------------------

--
-- Структура таблицы `provider`
--

CREATE TABLE `provider` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `inn` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nameprovider` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `provider`
--

INSERT INTO `provider` (`id`, `address`, `inn`, `nameprovider`, `name`) VALUES
(7, 'cxz', '123456789001', 'qwert', NULL),
(8, 'Улица гоголя', '123456789009', 'МММ', NULL),
(9, 'Улица 69', '123456789002', 'ЖМЖ', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `record`
--

CREATE TABLE `record` (
  `id` bigint NOT NULL,
  `daterecord` date DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  `employer_id` bigint DEFAULT NULL,
  `service_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `record`
--

INSERT INTO `record` (`id`, `daterecord`, `client_id`, `employer_id`, `service_id`) VALUES
(4, '2022-11-30', 4, 1, 2),
(5, '2022-12-16', 4, 1, 2),
(6, '2022-12-09', NULL, 2, 2),
(7, '2022-12-25', NULL, 1, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `service`
--

CREATE TABLE `service` (
  `id` bigint NOT NULL,
  `nameservice` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `service`
--

INSERT INTO `service` (`id`, `nameservice`, `price`) VALUES
(2, 'Бритьебороды', 1500),
(3, 'Стрижка под барсука', 2500),
(4, 'Покраска боробы', 2100),
(5, 'qwerty', 12345),
(6, 'xcvbnm,', 546545),
(8, 'qwertyj', 2356),
(9, 'qwertygfgt', 5),
(10, 'ячсми', 1234567);

-- --------------------------------------------------------

--
-- Структура таблицы `sick_leave`
--

CREATE TABLE `sick_leave` (
  `id` bigint NOT NULL,
  `datebegin` date DEFAULT NULL,
  `dateend` date DEFAULT NULL,
  `employer_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `sick_leave`
--

INSERT INTO `sick_leave` (`id`, `datebegin`, `dateend`, `employer_id`) VALUES
(1, '2022-12-11', '2022-12-15', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `stock`
--

CREATE TABLE `stock` (
  `id` bigint NOT NULL,
  `address` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `stock`
--

INSERT INTO `stock` (`id`, `address`) VALUES
(9, 'йцуе'),
(12, 'Улица 77'),
(13, 'Твеская'),
(15, 'defgdfn'),
(16, 'чпрпорл');

-- --------------------------------------------------------

--
-- Структура таблицы `supply`
--

CREATE TABLE `supply` (
  `id` bigint NOT NULL,
  `datesupply` date DEFAULT NULL,
  `price` double NOT NULL,
  `valuesupply` int NOT NULL,
  `provider_id` bigint DEFAULT NULL,
  `stock_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `supply`
--

INSERT INTO `supply` (`id`, `datesupply`, `price`, `valuesupply`, `provider_id`, `stock_id`) VALUES
(17, '2022-12-08', 1, 6666666, 7, NULL),
(18, '2022-12-07', 2, 1, 7, NULL),
(19, '2022-12-07', 12324, 12345, 7, NULL),
(20, '2022-12-08', 1111, 111, 7, NULL),
(21, '2022-12-15', 45678, 3456, 7, NULL),
(22, '2022-12-22', 234567, 123456, 7, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `tax_report`
--

CREATE TABLE `tax_report` (
  `id` bigint NOT NULL,
  `datebegin` date DEFAULT NULL,
  `dateend` date DEFAULT NULL,
  `datereport` date DEFAULT NULL,
  `taxvalue` double NOT NULL,
  `valuesells` double NOT NULL,
  `employer_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `tax_report`
--

INSERT INTO `tax_report` (`id`, `datebegin`, `dateend`, `datereport`, `taxvalue`, `valuesells`, `employer_id`) VALUES
(4, '2022-12-20', '2022-12-30', '2022-12-07', 1560, 12000, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `user`
--

INSERT INTO `user` (`id`, `active`, `email`, `password`) VALUES
(14, b'1', 'ilion23082003@gmail.com', '$2a$10$91gSHnCaeHchR3bnnr12JOPfImr4hhUljmlC9SICMJlmAtUomTcz2'),
(16, b'1', 'ilion2308@gmail.com', '$2a$10$91gSHnCaeHchR3bnnr12JOPfImr4hhUljmlC9SICMJlmAtUomTcz2'),
(45, b'0', 'ilion23082005@gmail.com', '$2a$10$91gSHnCaeHchR3bnnr12JOPfImr4hhUljmlC9SICMJlmAtUomTcz2'),
(46, b'0', 'qwer@rambler.ru', '$2a$10$l3B9hIspu5bphnlv523zl.92vL/9Rrp4AYHybJSbdgz7yLsU9hhti'),
(47, b'0', '322456787@rambler.ru', '$2a$10$DbMmPDh4gT6PzsUTDsNWpOESMvemA5KnDcTiy6SwUg95Lwg/CF36y'),
(48, b'0', 'zxcvbn23082003@gmail.com', '$2a$10$X8DWyJsFn91XFW2xI71FVuB1lyPZ/nM.Qi.zleSXZE2kvICv6unJW'),
(49, b'0', 'admin@rambler.ru', '$2a$10$SdqhMddRwKLW7LiimoZNa.o8fJuy5sxz7usTEq9K9Hgej.m9zYf8e'),
(50, b'0', 'i230820032@gmail.com', '$2a$10$DURa4FbRqiUnMBDI7h/fLOc5vOvgmtugsmtCI2Hjd9RbSc5aAvbbO'),
(51, b'0', 'novikov@rambler.ru', '$2a$10$jCbvZ5Jz.sSY/v566mcQqeHr5fHNkVrYWfTNo6UsoZZSGqexMDTXq'),
(52, b'0', 'o@rambler.ru', '$2a$10$CK2muRyakImKfFo68HSYB.0YdQWFsK9dUAcoTCrsEhK4Wy0c5JzZ2'),
(53, b'0', 'newAdm111@gmailr.ru', '$2a$10$XTt6T2OY2jUzMRUx08b8P.Ds5RthiSyEE7gM9QPGmh0q23u652TvS'),
(54, b'0', 'cladd@gmail.com', '$2a$10$2sIvam.1I4E9/dCdOB5Vb.By7xOGQ1VcK4WX9X.ieIjXU91hJcXKi'),
(55, b'0', 'kadr@gmail.com', '$2a$10$hapzu5E72eqwF4k25iJjvOwu6QxhWbp9El1ucxZCiiWu16cAxw.Mm'),
(56, b'0', 'buh@gmail.com', '$2a$10$MhNHb/5WHahH6I2dYWtCl.CeMMWAyaf3YJU/AnWyn6TZf/ZmmP8Aq'),
(57, b'1', 'adm@gmail.com', '$2a$10$IBydMhbs8Z8GMUTdMxf4xu4WES1B1VeErKNL/HqUeQDIbQtNAaSui'),
(58, b'0', 'ad@rambler.ru', '$2a$10$yMBxFBQf4D5DO70GXk9KTu61lp61A9Vkyc0cAnW4ORJOEaBehc9fm'),
(59, b'0', 'xxcfcxxc@rambler.ru', '$2a$10$NBw/e/C1zZHaVliMCdBxt.kGM.VvwHp9uOJBNbNHJwPA1nCXMT4V2');

-- --------------------------------------------------------

--
-- Структура таблицы `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `roles` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles`) VALUES
(14, 'CLIENT'),
(16, 'CLIENT'),
(45, 'ADMIN'),
(46, 'ADMIN'),
(47, 'ADMIN'),
(48, 'ADMIN'),
(49, 'ADMIN'),
(50, 'KADR'),
(51, 'ADMIN'),
(52, 'ADMIN'),
(53, 'ADMIN'),
(54, 'STOCKER'),
(55, 'KADR'),
(56, 'BUHGALTER'),
(57, 'ADMIN'),
(58, 'ADMIN'),
(59, 'ADMIN');

-- --------------------------------------------------------

--
-- Структура таблицы `vacation`
--

CREATE TABLE `vacation` (
  `id` bigint NOT NULL,
  `datebegin` date DEFAULT NULL,
  `dateend` date DEFAULT NULL,
  `employer_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `vacation`
--

INSERT INTO `vacation` (`id`, `datebegin`, `dateend`, `employer_id`) VALUES
(1, '2022-12-07', '2022-12-21', 1);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1ixfyfepst9sjbo9op1v65fg0` (`user_id`);

--
-- Индексы таблицы `consumption`
--
ALTER TABLE `consumption`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_qxr7vvlv0bb0gkatlqd8v83hn` (`supply_id`);

--
-- Индексы таблицы `employer`
--
ALTER TABLE `employer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK255te3x4baymj6h7v73bw39ca` (`post_id`),
  ADD KEY `FK81pr23jf16cs0wxoh94yj2nug` (`user_id`);

--
-- Индексы таблицы `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_2towvqqylnqo7dmb2nhl34ef6` (`record_id`);

--
-- Индексы таблицы `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `material_supply`
--
ALTER TABLE `material_supply`
  ADD KEY `FKmh89ep1qv404ceuaevjmk8ko4` (`material_id`),
  ADD KEY `FK74pyag6dp3fwomt66lro07aql` (`supply_id`);

--
-- Индексы таблицы `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKii82e8vwfjf606lsybnio7i19` (`client_id`),
  ADD KEY `FK1di00uos0gcxc8n2uc0g52liw` (`employer_id`),
  ADD KEY `FKkir6x8kyse3j3m2ujwoqvjp6a` (`service_id`);

--
-- Индексы таблицы `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `sick_leave`
--
ALTER TABLE `sick_leave`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf5eyy1fyer5x0apvij8i82b8x` (`employer_id`);

--
-- Индексы таблицы `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `supply`
--
ALTER TABLE `supply`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKloo573ekgsg3g6m3lmuhkp1mp` (`provider_id`),
  ADD KEY `FKbwaw4bkkbfjydyjfg72ntqbbd` (`stock_id`);

--
-- Индексы таблицы `tax_report`
--
ALTER TABLE `tax_report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKb3r9h24ykv53gjy0w1d14sl6o` (`employer_id`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- Индексы таблицы `vacation`
--
ALTER TABLE `vacation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcufxa5kkl7iuh7yyvmwk5p1t7` (`employer_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `client`
--
ALTER TABLE `client`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Уникальный идентификатор\r\n', AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `consumption`
--
ALTER TABLE `consumption`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `employer`
--
ALTER TABLE `employer`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `income`
--
ALTER TABLE `income`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT для таблицы `material`
--
ALTER TABLE `material`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `post`
--
ALTER TABLE `post`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `provider`
--
ALTER TABLE `provider`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `record`
--
ALTER TABLE `record`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `service`
--
ALTER TABLE `service`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT для таблицы `sick_leave`
--
ALTER TABLE `sick_leave`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `stock`
--
ALTER TABLE `stock`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT для таблицы `supply`
--
ALTER TABLE `supply`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT для таблицы `tax_report`
--
ALTER TABLE `tax_report`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;

--
-- AUTO_INCREMENT для таблицы `vacation`
--
ALTER TABLE `vacation`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKk1fi84oi1yyuswr40h38kjy1s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `consumption`
--
ALTER TABLE `consumption`
  ADD CONSTRAINT `FKh67qm6cka7e7rrmdy1yqrfwkf` FOREIGN KEY (`supply_id`) REFERENCES `supply` (`id`);

--
-- Ограничения внешнего ключа таблицы `employer`
--
ALTER TABLE `employer`
  ADD CONSTRAINT `FK255te3x4baymj6h7v73bw39ca` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  ADD CONSTRAINT `FK81pr23jf16cs0wxoh94yj2nug` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `income`
--
ALTER TABLE `income`
  ADD CONSTRAINT `FKs8m7kpma1w30dlo68rt4j00ta` FOREIGN KEY (`record_id`) REFERENCES `record` (`id`);

--
-- Ограничения внешнего ключа таблицы `material_supply`
--
ALTER TABLE `material_supply`
  ADD CONSTRAINT `FK74pyag6dp3fwomt66lro07aql` FOREIGN KEY (`supply_id`) REFERENCES `supply` (`id`),
  ADD CONSTRAINT `FKmh89ep1qv404ceuaevjmk8ko4` FOREIGN KEY (`material_id`) REFERENCES `material` (`id`);

--
-- Ограничения внешнего ключа таблицы `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `FK1di00uos0gcxc8n2uc0g52liw` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  ADD CONSTRAINT `FKii82e8vwfjf606lsybnio7i19` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKkir6x8kyse3j3m2ujwoqvjp6a` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`);

--
-- Ограничения внешнего ключа таблицы `sick_leave`
--
ALTER TABLE `sick_leave`
  ADD CONSTRAINT `FKf5eyy1fyer5x0apvij8i82b8x` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);

--
-- Ограничения внешнего ключа таблицы `supply`
--
ALTER TABLE `supply`
  ADD CONSTRAINT `FKbwaw4bkkbfjydyjfg72ntqbbd` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`),
  ADD CONSTRAINT `FKloo573ekgsg3g6m3lmuhkp1mp` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`id`);

--
-- Ограничения внешнего ключа таблицы `tax_report`
--
ALTER TABLE `tax_report`
  ADD CONSTRAINT `FKb3r9h24ykv53gjy0w1d14sl6o` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Ограничения внешнего ключа таблицы `vacation`
--
ALTER TABLE `vacation`
  ADD CONSTRAINT `FKcufxa5kkl7iuh7yyvmwk5p1t7` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
