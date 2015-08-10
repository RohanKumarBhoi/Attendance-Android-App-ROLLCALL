-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: sql300.byetcluster.com
-- Generation Time: Nov 18, 2014 at 04:55 PM
-- Server version: 5.6.21-70.0
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `b7_15501502_attendance`
--

-- --------------------------------------------------------

--
-- Table structure for table `profs`
--

CREATE TABLE IF NOT EXISTS `profs` (
  `email` varchar(50) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sub1` varchar(20) DEFAULT NULL,
  `sub2` varchar(20) DEFAULT NULL,
  `sub3` varchar(20) DEFAULT NULL,
  `sub4` varchar(20) DEFAULT NULL,
  `sub5` varchar(20) DEFAULT NULL,
  `sub6` varchar(20) DEFAULT NULL,
  `sub7` varchar(20) DEFAULT NULL,
  `sub8` varchar(20) DEFAULT NULL,
  `sub9` varchar(20) DEFAULT NULL,
  `sub10` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profs`
--

INSERT INTO `profs` (`email`, `pass`, `name`, `sub1`, `sub2`, `sub3`, `sub4`, `sub5`, `sub6`, `sub7`, `sub8`, `sub9`, `sub10`) VALUES
('mns@nitrkl.ac.in', 'manmath', 'Manmath', '_2012_cs1_cs241', NULL, '_2012_cs2_cs241', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('rkm@nitrkl.ac.in', 'ramesh', 'Ramesh', '_2012_cs1_cs341', '_2012_cs2_cs341', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `_2012_cs1_cs241`
--

CREATE TABLE IF NOT EXISTS `_2012_cs1_cs241` (
  `name` varchar(50) NOT NULL,
  `roll` varchar(10) NOT NULL,
  PRIMARY KEY (`roll`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_2012_cs1_cs241`
--

INSERT INTO `_2012_cs1_cs241` (`name`, `roll`) VALUES
('Bhuban', '112cs0144'),
('Rohan', '112cs0140'),
('Arjun', '112cs0134'),
('Anup', '112cs0454'),
('Shahbaz', '112cs0182'),
('Vishnu', '112cs0188'),
('Himanshu', '112cs0135');

-- --------------------------------------------------------

--
-- Table structure for table `_2012_cs1_cs341`
--

CREATE TABLE IF NOT EXISTS `_2012_cs1_cs341` (
  `name` varchar(50) NOT NULL,
  `roll` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_2012_cs1_cs341`
--

INSERT INTO `_2012_cs1_cs341` (`name`, `roll`) VALUES
('Bhuban', '112cs0144'),
('Rohan', '112cs0140'),
('Arjun', '112cs0134'),
('Anup', '112cs0454'),
('Shahbaz', '112cs0182'),
('Vishnu', '112cs0188'),
('Himanshu', '112cs0135');

-- --------------------------------------------------------

--
-- Table structure for table `_2012_cs2_cs241`
--

CREATE TABLE IF NOT EXISTS `_2012_cs2_cs241` (
  `name` varchar(50) NOT NULL,
  `roll` varchar(10) NOT NULL,
  `18/11/2014` int(11) DEFAULT NULL,
  `20/11/2014` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_2012_cs2_cs241`
--

INSERT INTO `_2012_cs2_cs241` (`name`, `roll`, `18/11/2014`, `20/11/2014`) VALUES
('Soumya', '712cs1040', 1, 1),
('Abhishek', '712cs2049', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `_2012_cs2_cs341`
--

CREATE TABLE IF NOT EXISTS `_2012_cs2_cs341` (
  `name` varchar(50) NOT NULL,
  `roll` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `_2012_cs2_cs341`
--

INSERT INTO `_2012_cs2_cs341` (`name`, `roll`) VALUES
('Soumya', '712cs1040'),
('Abhishek', '712cs2049');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
