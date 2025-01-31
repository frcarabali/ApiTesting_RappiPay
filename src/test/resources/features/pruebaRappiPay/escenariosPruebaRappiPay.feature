Feature: API Testing de Shazam
  Como tester de QA Automation
  Quiero validar la funcionalidad de la API de Shazam
  Para asegurarme de que responde correctamente

  @scenery1 @all
  Scenario: Obtener detalles de una canción
    Given el usuario realiza una petición GET al endpoint "/songs/v2/get-details?id=1217912247&l=en-US"
    When la petición es exitosa
    Then el código de respuesta es 200
    And el JSON de respuesta contiene el campo "title"
  @scenery2 @all
  Scenario: Detectar una canción
    Given el usuario realiza una petición POST al endpoint "/songs/detect" con un payload válido
    When la petición es procesada correctamente
    Then el código de respuesta es 204
    And el JSON de respuesta contiene el campo "track"