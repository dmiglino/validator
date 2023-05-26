# validator
Input Validator Strategy

Try mvn clean test to run all tests.

Post to http://localhost:8080/validate with a VALID input like:
{
    "First_Name":"Diego",
    "Mid_Name":"Martin",
    "Last_Name":"Miglino",
    "State":"Aasdf fdsa",
    "Address":"TEST DIEGO 6"
}

Post to http://localhost:8080/validate with a INVALID input like:
{
    "First_Name":"123",
    "Mid_Name":"a-d",
    "Last_Name":"...",
    "State":"123",
    "Address":"TEST-DIEGO.6"
}
