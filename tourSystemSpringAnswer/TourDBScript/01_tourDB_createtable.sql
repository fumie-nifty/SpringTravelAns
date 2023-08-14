--/*================================================================*/
--/* テーブル削除            */
--/*================================================================*/

use tourdb;

DROP TABLE IF EXISTS Destination;
DROP TABLE IF EXISTS City;
DROP TABLE IF EXISTS Airport;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Member;
DROP TABLE IF EXISTS HotelMaster;
DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS FlightMaster;
DROP TABLE IF EXISTS Flight;
DROP TABLE IF EXISTS TourMaster;
DROP TABLE IF EXISTS Tour;
DROP TABLE IF EXISTS OrderMaster;
DROP TABLE IF EXISTS OrderDetail;
DROP TABLE IF EXISTS MemberNumbering;


--/*================================================================*/
--/* Destination（）テーブル         */
--/*================================================================*/
create table Destination (
  DestinationCode varchar(2),
  Name varchar(6),
  primary key(DestinationCode)
)engine=InnoDB;


--/*================================================================*/
--/* City（）テーブル         */
--/*================================================================*/
create table City (
  CityCode varchar(2),
  Name varchar(10),
  primary key(CityCode)
)engine=InnoDB;


--/*================================================================*/
--/* Airport（）テーブル         */
--/*================================================================*/
create table Airport (
  AirportCode varchar(3),
  Name varchar(10),
  primary key(AirportCode)
)engine=InnoDB;


--/*================================================================*/
--/* Category（）テーブル         */
--/*================================================================*/
create table Category (
  CategoryNo integer,
  CategoryCode varchar(3),
  CategoryName varchar(10),
  primary key(CategoryNo)
)engine=InnoDB;


--/*================================================================*/
--/* Member（）テーブル         */
--/*================================================================*/
create table Member (
  MemberCode varchar(9),
  Name varchar(20),
  Password varchar(15),
  Role varchar(15),
  Mail varchar(100),
  ZipCode varchar(8),
  Prefecture varchar(8),
  Address varchar(50),
  Tel varchar(13),
  primary key(MemberCode)
)engine=InnoDB;




--/*================================================================*/
--/* HotelMaster（）テーブル         */
--/*================================================================*/
create table HotelMaster (
  HotelCode varchar(6),
  Name varchar(30),
  CityCode varchar(2),
  Grade varchar(2),
  BasicPrice integer,
  primary key(HotelCode)
)engine=InnoDB;


--/*================================================================*/
--/* Hotel（）テーブル         */
--/*================================================================*/
create table Hotel (
  ItemCode varchar(9),
  HotelCode varchar(6),
  Date date,
  Price integer,
  Stock integer,
  primary key(ItemCode)
)engine=InnoDB;


--/*================================================================*/
--/* FlightMaster（）テーブル         */
--/*================================================================*/
create table FlightMaster (
  FlightCode varchar(6),
  DepartureTime time,
  ArrivalTime time,
  DepartureAirportCode varchar(3),
  ArrivalAirportCode varchar(3),
  BasicPrice integer,
  primary key(FlightCode)
)engine=InnoDB;


--/*================================================================*/
--/* Flight（）テーブル         */
--/*================================================================*/
create table Flight (
  ItemCode varchar(9),
  FlightCode varchar(6),
  Date date,
  Price integer,
  Stock integer,
  primary key(ItemCode)
)engine=InnoDB;



--/*================================================================*/
--/* TourMaster（）テーブル         */
--/*================================================================*/
create table TourMaster (
  TourCode varchar(6),
  Name varchar(50),
  DestinationCode varchar(2),
  Days integer,
  Nights integer,
  BasicPrice integer,
  HotelCode varchar(6),
  OutwardFlightCode varchar(6),
  HomewardFlightCode varchar(6),
  primary key(TourCode)
)engine=InnoDB;


--/*================================================================*/
--/* Tour（）テーブル         */
--/*================================================================*/
create table Tour (
  ItemCode varchar(9),
  TourCode varchar(6),
  Date date,
  Price integer,
  FirstHotelItemCode varchar(9),
  OutwardFlightItemCode varchar(9),
  HomewardFlightItemCode varchar(9),
  Stock integer,
  primary key(ItemCode)
)engine=InnoDB;

--/*================================================================*/
--/* OrderMaster（）テーブル         */
--/*================================================================*/
create table OrderMaster (
  OrderNo integer auto_increment,
  OrderDate date,
  OrderTotal integer,
  MemberCode varchar(9),
  Payment varchar(2),
  primary key(OrderNo)
)engine=InnoDB;


--/*================================================================*/
--/* OrderDetail（）テーブル         */
--/*================================================================*/
create table OrderDetail (
  OrderNo integer,
  ItemCode varchar(9),
  ItemName varchar(50),
  Price integer,
  Quantity integer
)engine=InnoDB;


alter table OrderDetail add primary key (
    OrderNo,
    ItemCode
);

--/*================================================================*/
--/* MemberNumbering（）テーブル         */
--/*================================================================*/
CREATE TABLE MemberNumbering (
  MemberCode INTEGER(4) NOT NULL
)engine=InnoDB;

