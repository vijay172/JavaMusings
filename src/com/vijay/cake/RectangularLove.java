package com.vijay.cake;

/**
 * Created by vkbalakr on 6/26/17.
 */
public class RectangularLove {

    public static class XOverlap {

        private int startingPoint;
        private int width;
        public static final XOverlap NO_OVERLAP = new XOverlap();
        public XOverlap() {}

        public XOverlap(int startingPoint, int width) {
            this.startingPoint = startingPoint;
            this.width = width;
        }

        public int getStartingPoint() {
            return startingPoint;
        }
        public int getWidth() {
            return width;
        }
    }

    public static class YOverlap {

        public int getStartingPoint() {
            return startingPoint;
        }

        public int getHt() {
            return ht;
        }

        private int startingPoint;
        private int ht;
        public static final YOverlap NO_OVERLAP = new YOverlap();
        public YOverlap() {}
        public YOverlap(int startingPoint, int ht) {
            this.startingPoint = startingPoint;
            this.ht = ht;
        }


    }

    public static YOverlap findYOverlap(int y1, int ht1, int y2, int ht2) {

        int highestStartPoint = Math.max(y1, y2);
        int lowestEndPoint = Math.min(y1 +ht1, y2 + ht2);
         if(highestStartPoint >= lowestEndPoint) {
             return YOverlap.NO_OVERLAP;
         }
         int overlapHeight = lowestEndPoint - highestStartPoint;
         return new YOverlap(highestStartPoint, overlapHeight);
    }

    public static XOverlap findXOverlap(int x1, int w1, int x2, int w2) {



        int highestStartPoint = Math.max(x1, x2);
        int lowestEndPoint = Math.min(x1+w1, x2+w2);

        if (highestStartPoint >= lowestEndPoint) {
            return XOverlap.NO_OVERLAP;
        }

        int overlapWidth = lowestEndPoint - highestStartPoint;
        return new XOverlap(highestStartPoint, overlapWidth);
    }


    public int findXOverlap(Rectangle rect1, Rectangle rect2) {
        int rect1LeftX = rect1.getLeftX();
        int rect1Width = rect1.getWidth();
        int rect2LeftX = rect2.getLeftX();
        int rect2Width = rect2.getWidth();
        int leftXOverlap = 0;
        int rightXOverlap = 0;
        if (rect1LeftX < rect2LeftX) {
            rightXOverlap = rect1LeftX + rect1Width;

            if (rightXOverlap > rect2LeftX) {
                leftXOverlap = rect1LeftX;
            } else if (rightXOverlap == rect2LeftX) {
                //touch each other
                leftXOverlap = rect1LeftX;
            }
        } else if (rect1LeftX > rect2LeftX) {
            rightXOverlap = rect2LeftX + rect2Width;
            if (rightXOverlap > rect1LeftX) {
                leftXOverlap = rect2LeftX;//overlap
            } else if (rightXOverlap == rect1LeftX) {
                //touch each other
                leftXOverlap = rect2LeftX;
            }
        }
        return leftXOverlap;
    }

    public static void main(String[] args) {
        Rectangle rect1 = new Rectangle(1,1,6,8);
        Rectangle rect2 = new Rectangle(3,4,6,8);
        XOverlap xoverlap = findXOverlap(rect1.leftX, rect1.width, rect2.leftX, rect2.width);
        YOverlap yoverlap = findYOverlap(rect1.bottomY, rect1.height, rect2.bottomY, rect2.height);

        int leftx = xoverlap.startingPoint - xoverlap.width;
        int bottomy = yoverlap.startingPoint - yoverlap.ht;

        Rectangle result = new Rectangle(leftx, bottomy, xoverlap.width, yoverlap.ht);
        System.out.println(result);
    }

    public static class Rectangle {

        // "zero" rectangle
        public static final Rectangle NO_RECTANGLE = new Rectangle();

        // coordinates of bottom left corner
        private int leftX;
        private int bottomY;

        // dimensions
        private int width;
        private int height;

        public Rectangle() {}

        public Rectangle(int leftX, int bottomY, int width, int height) {
            this.leftX = leftX;
            this.bottomY = bottomY;
            this.width  = width;
            this.height = height;
        }

        public int getLeftX() {
            return leftX;
        }

        public int getBottomY() {
            return bottomY;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }


}
