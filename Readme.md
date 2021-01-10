# Rgb slider color application 

### How to run it:

1. Clone the repository locally.
2. Import it in a Java IDE (e.g. Intellij Idea).
3. Add environment variables "DbUser" and "DbPassword" in the IDE with your corresponding ones. 
3. Run the application.
4. Navigate to src/main/resources/static and open the index.html file in a browser (e.g. Chrome). 
5. Adjust the sliders to the desired values and you can see the resulting color.

### How it works
Back-end receives the RGB values from front-end, persists them and sends them back to the front-end.
Front-end receives the values (that were already sent by him, haha) and displays the color based on the three values.
Communication b/w BE and FE is carried over Websocket.

### How it looks
![How it looks](https://i.imgur.com/AwMrtxr.jpg) 

### Technologies used
Spring WebFlux, JPA/Hibernate, PostgreSQL, Websocket

