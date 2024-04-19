**Maturita programování:**
JavaFX Programování
Základy v Hello-Application
1. Titulek
```language
stage.setTitle("Titulek aplikace");
```
2. Ikona
```language
stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
```
3. Výška, Šířka aplikace (Defaultní podle SceneBuilderu)
```language
FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
```
4. Zakázat měnění šířky aplikace
```language
stage.setResizable(false);
```
