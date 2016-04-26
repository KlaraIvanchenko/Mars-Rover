"use strict";

Main();

const Directions = {
    order: ["N", "E", "S", "W"],
    "N": {
        x: 0,
        y: 1
    },
    "E": {
        x: 1,
        y: 0
    },
    "S": {
        x: 0,
        y: -1
    },
    "W": {
        x: -1,
        y: 0
    }
};

const map = {
    width: 0,
    height: 0
};

class Rover {
    constructor(x, y, direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    do(command) {
        switch(command) {
            case "L":
                return this.turnLeft();
            case "R":
                return this.turnRight();
            case "M":
                return this.move();
        }
    }
    turnLeft() {
        const currentDirection = Directions.order.indexOf(this._directionName);
        const directionToTheLeft = Math.abs((currentDirection - 1) % Directions.order.length);
        this.direction = Directions.order[directionToTheLeft];
    }
    turnRight() {
        const currentDirection = Directions.order.indexOf(this._directionName);
        const directionToTheRight = (currentDirection + 1) % Directions.order.length;
        this.direction = Directions.order[directionToTheRight];
    }
    move() {
        if (this.x += this._direction.x > map.width
            || this.x += this._direction.x < 0
           ) {
               return;
           }
        if (this.y += this._direction.y > map.height
            || this.y += this._direction.y < 0
           ) {
               return;
           }
        this.x += this._direction.x;
        this.y += this._direction.y;
    }
    set direction(direction) {
        this._direction = Directions[direction];
        this._directionName = direction;
    }
    get position() {
        return `${this.x} ${this.y} ${this._directionName}`;
    }
}

function Main(){
    let index = 0, rover;
    process.stdin.on('readable', () => {
        let chunk = process.stdin.read();
        if (chunk === null) {
            return;
        }
        chunk = chunk.toString();
        if (index === 0) {
            processMapSize(chunk);
        } else if (index % 2 === 1) {
            rover = createRover(chunk);
        } else {
            moveRover(chunk, rover);
            process.stdout.write(`${rover.position}\n`);
        }

        ++index;
    });
}

function processMapSize(chunk) {
    const coords = chunk.split(" ");
    map.width = parseInt(coords[0]);
    map.height = parseInt(coords[1]);
}

function createRover(chunk) {
    const coords = chunk.split(" ");
    return new Rover(
        parseInt(coords[0]),
        parseInt(coords[1]),
        coords[2][0]
    );
}

function moveRover(chunk, rover) {
    chunk.split("").forEach(command => rover.do(command));
}
