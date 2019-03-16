-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mar 16, 2019 alle 12:24
-- Versione del server: 10.1.37-MariaDB
-- Versione PHP: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hibernatecrudexample`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `progetti`
--

CREATE TABLE `progetti` (
  `ID` int(11) NOT NULL,
  `TITOLO` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `progetti`
--

INSERT INTO `progetti` (`ID`, `TITOLO`) VALUES
(4, 'Area Lab'),
(5, 'Sicurezza Marconi'),
(6, 'Olimpiadi di Informatica');

-- --------------------------------------------------------

--
-- Struttura della tabella `studenti`
--

CREATE TABLE `studenti` (
  `ID` int(11) NOT NULL,
  `NOME` text NOT NULL,
  `COGNOME` text NOT NULL,
  `CLASSE` text NOT NULL,
  `DATA_DI_NASCITA` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `studenti`
--

INSERT INTO `studenti` (`ID`, `NOME`, `COGNOME`, `CLASSE`, `DATA_DI_NASCITA`) VALUES
(3, 'Mario', 'Rossi', '5BI', '2001-10-09'),
(4, 'Luigi', 'Neri', '5BI', '2001-10-07');

-- --------------------------------------------------------

--
-- Struttura della tabella `studenti_progetti`
--

CREATE TABLE `studenti_progetti` (
  `ID_STUDENTI` int(11) NOT NULL,
  `ID_PROGETTI` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `studenti_progetti`
--

INSERT INTO `studenti_progetti` (`ID_STUDENTI`, `ID_PROGETTI`) VALUES
(3, 4),
(3, 5),
(4, 5),
(4, 6);

-- --------------------------------------------------------

--
-- Struttura della tabella `voti`
--

CREATE TABLE `voti` (
  `ID` int(11) NOT NULL,
  `VOTO` int(11) NOT NULL,
  `DATA` date NOT NULL,
  `ID_STUDENTE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `voti`
--

INSERT INTO `voti` (`ID`, `VOTO`, `DATA`, `ID_STUDENTE`) VALUES
(3, 8, '2019-10-09', 3),
(4, 4, '2019-10-10', 3);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `progetti`
--
ALTER TABLE `progetti`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `studenti`
--
ALTER TABLE `studenti`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `studenti_progetti`
--
ALTER TABLE `studenti_progetti`
  ADD PRIMARY KEY (`ID_STUDENTI`,`ID_PROGETTI`),
  ADD KEY `ID_PROGETTI` (`ID_PROGETTI`);

--
-- Indici per le tabelle `voti`
--
ALTER TABLE `voti`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_STUDENTE` (`ID_STUDENTE`) USING BTREE;

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `progetti`
--
ALTER TABLE `progetti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `studenti`
--
ALTER TABLE `studenti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT per la tabella `voti`
--
ALTER TABLE `voti`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `studenti_progetti`
--
ALTER TABLE `studenti_progetti`
  ADD CONSTRAINT `studenti_progetti_ibfk_1` FOREIGN KEY (`ID_STUDENTI`) REFERENCES `studenti` (`ID`),
  ADD CONSTRAINT `studenti_progetti_ibfk_2` FOREIGN KEY (`ID_PROGETTI`) REFERENCES `progetti` (`ID`);

--
-- Limiti per la tabella `voti`
--
ALTER TABLE `voti`
  ADD CONSTRAINT `voti_ibfk_1` FOREIGN KEY (`ID_STUDENTE`) REFERENCES `studenti` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
