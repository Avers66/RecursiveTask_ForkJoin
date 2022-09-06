public class Elevator {
    private int currentFloor = 1;
    private int maxFloor;
    private int minFloor;

    public Elevator(int minFloor, int maxFloor) {
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveDown() {
        if (currentFloor > minFloor) {
            currentFloor = currentFloor - 1;
        }
    }

    public void moveUp() {
        if (currentFloor < maxFloor) {
            currentFloor = currentFloor + 1;
        }
    }

    public void move(int floor) {
        if (floor <= maxFloor && floor >= minFloor) {
            while (currentFloor > floor) {
                moveDown();
                System.out.println("Этаж " + currentFloor);
            }
            while (currentFloor < floor) {
                moveUp();
                System.out.println("Этаж " + currentFloor);
            }
        } else {
            System.out.println("Неправильно задан этаж");
        }

    }


}
