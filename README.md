
# Game of Life: Petri Dish Edition

## Overview
The Game of Life: Petri Dish Edition is an open-source simulation based on John Conway's Game of Life, a cellular automaton devised in the 1970s. This project aims to provide a visual and interactive representation of cellular growth within a petri dish environment, demonstrating complex, emergent behaviors from simple rules.

## Files
- **Cell.java**: Defines the cell object, its state (alive or dead), and its behavior within the grid, including rules for survival, death, and reproduction.
- **Game.java**: Manages the game state, including the grid of cells, and handles the update logic according to the Game of Life rules.
- **Renderer.java**: Responsible for rendering the grid to a visual interface, updating the display as the game progresses.

## Installation
To run this simulation:
1. Clone this repository:
   ```bash
   git clone https://github.com/4n4c0nd4RSA/game-of-life-petri.git
   ```
2. Compile the Java files:
   ```bash
   javac Game.java Cell.java Renderer.java
   ```
3. Run the simulation:
   ```bash
   java Game
   ```

## Usage
Once the program is running, it will display a grid representing a petri dish where cells evolve from generation to generation according to predefined rules.

## Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests, or open issues for bugs and feature requests.

## License
MIT License

```license
MIT License

Copyright (c) 2022 [Your Name]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
