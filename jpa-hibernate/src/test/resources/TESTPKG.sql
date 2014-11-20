CREATE OR REPLACE PACKAGE TESTPKG AS 

  /* TODO enter package declarations (types, exceptions, methods etc) here */ 
  
  PROCEDURE HelloWorld(outParam1 OUT VARCHAR2);

END TESTPKG;
/


CREATE OR REPLACE PACKAGE BODY TESTPKG AS

  PROCEDURE HelloWorld(outParam1 OUT VARCHAR2) AS
  BEGIN
    outParam1 := 'Hello World OUT parameter';
  END HelloWorld;

END TESTPKG;
/
