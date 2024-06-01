# Tetris

## Sơ đồ mô hình quản lý các khối

<div style="text-align:center">
  <img src="img/tetris_block.png"  alt="block class"/>
</div>

Mô hình quản lý các khối trong game Tetris có thể được chia thành các thành phần chính sau:

- Tiles: Mỗi khối trong Tetris có một hình dạng và màu sắc riêng.
  Có 7 hình dạng phổ biến bao gồm I, O, T, S, Z, J và L.
  Mỗi khối được biểu diễn bằng một ma trận gồm các ô vuông (Tile),
  trong đó mỗi ô vuông có thể có hoặc không có màu.
- startOffset: biểu diễn vị trí bắt đầu, xuất hiện của khối
- currentOffset: biểu diễn vị trí hiện tại của khối
- state: biểu diễn trạng thái xoay của khối

các phương thức khác nhằm giúp ích cho việc quản lý vị trí của khối như xoay,
đặt lại vị trí, di chuyển sang trái phải lên xuống

---

## Game loop

```java
public void run() {
    double drawInterval = (double) 1_000_000_000 / Main.FPS;
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (gameThread != null) {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;

        if (delta >= 1) {
            update();
            repaint();
            delta--;
        }
    }
}
```

Với thuật toán này sẽ đảm bảo FPS đã được khai báo

---

### Tính năng đoán khối tiếp theo

Khi game bắt đầu sẽ hiển thị khối sẽ được xuất hiện tiếp theo sau khi khối hiện tại được đặt xuống

<div style="text-align:center">
  <img src="img/next block.png"  alt="next block"/>
</div>

---

### Tính năng giữ khối

Khi muốn giữ khối cần bấm [Space],
khối đó sẽ được lưu lại và hiển thị bên tay trái màn hình

Khi mới khởi tạo và chưa giữ khối nào

<div style="text-align:center">
  <img src="img/hold block when init.png"  alt="hold block when init"/>
</div>

Khi đã giữ khối

<div style="text-align:center">
  <img src="img/hold block when active.png"  alt="hold block when active"/>
</div>
