-- /*================================================================*/
-- /*  システム研修 データベース作成スクリプト               2021.01 */
-- /*  Spring ツアーシステム                                         */
-- /*  All Rights Reserved, Copyright(c) Fujitsu Learning Media Ltd. */
-- /*================================================================*/
-- /*================================================================*/
-- /*  tourdbデーターベースの作成                                       */
-- /*================================================================*/
drop database if exists tourdb;
create database tourdb;

--/*================================================================*/
--/*  ユーザー作成                                                  */
--/*================================================================*/
drop user if exists 'tourmysql'@localhost;
drop user if exists 'tourmysql'@'%';

create user 'tourmysql'@'localhost' identified with mysql_native_password by 'tourmysql';
create user 'tourmysql'@'%' identified with mysql_native_password by 'tourmysql';

--/*================================================================*/
--/*  権限設定                                                      */
--/*================================================================*/
grant all on tourdb.* to 'tourmysql'@localhost;
grant all on tourdb.* to 'tourmysql'@'%';


