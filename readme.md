Game brick breaker

Assets: https://jamiecross.itch.io/breakout-brick-breaker-game-tile-set-free

---

Result:

![img.png](img.png)

---

Step:

Bước 1: vẽ hình tròn - bóng

Bước 2: cho bóng di chuyển

Bước 3: vẽ thanh ngang

Bước 4: cho thanh ngang di chuyển và Kiểm tra va chạm với bóng

Bước 5: vẽ các thanh ngang ở trên làm vật cản và tính điểm

Bước 6: làm cơ chế tính điểm, level, mạng, tốc độ bóng

---

Load image:

```java
private BufferedImage image;
try {  
    image = ImageIO.read(new File("file name and path"));  
} catch (IOException ex) {  
    System.out.println("load failed: " + ex.getMessage());  
}
```

---

Change size image:

```java
var _image = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);  
g.drawImage(_image, x, y, this);
```