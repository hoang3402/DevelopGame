# Tetris

## Sơ đồ mô hình quản lý các khối

![img.png](img/tetris_block.png)

Mô hình quản lý các khối trong game Tetris có thể được chia thành các thành phần chính sau:

- Tiles: Mỗi khối trong Tetris có một hình dạng và màu sắc riêng.
  Có 7 hình dạng phổ biến bao gồm I, O, T, S, Z, J và L.
  Mỗi khối được biểu diễn bằng một ma trận gồm các ô vuông (Tile),
  trong đó mỗi ô vuông có thể có hoặc không có màu.
- startOffset: biểu diễn vị trí bắt đầu, xuất hiện của khối
- currentOffset: biểu diễn vị trí hiện tại của khối
- state: biểu diễn trạng thái xoay của khối

các phương thức khác nhằm giúp ích cho việc quản lý vị trí của khối như xoay, đặt lại vị trí, di
chuyển sang trái phải lên xuống

---

