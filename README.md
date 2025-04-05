# TicTacToe Game - Jetpack Compose

## Overview
TicTacToe is a classic game built with Kotlin and Jetpack Compose for Android. This project allows users to play against a friend or challenge an AI with three different difficulty levels. The game features a clean and minimalistic UI, providing a seamless and enjoyable experience.

## Features
- **Player vs Player mode (local)**
- **Player vs AI** with easy, medium, and hard difficulties
- **Pro Mode** for advanced gameplay
- **Clean and minimal UI** built using Jetpack Compose
- **Smooth navigation** with Compose Navigation

## Pro Mode
In **Pro Mode**, the classic gameplay takes a strategic twist. Once both players (or player and AI) have made **6 moves each**, the game begins removing the oldest moves in the order they were played.  

For example, if a player's moves were:
- [1,3], [3,1], [2,3]

Then, after the 6th move, the game will start clearing them in the same order:
1. First removes [1,3]  
2. Then [3,1]  
3. Then [2,3]  

This mechanic introduces a dynamic challenge where players must not only focus on winning, but also think ahead about losing their earlier moves. It keeps the board evolving and adds a whole new level of strategy.

## Tech Stack
- **Kotlin** - Modern programming language for Android development
- **Jetpack Compose** - Declarative UI framework for building native Android apps
- **Coroutines & Flows** - Handling asynchronous operations efficiently
- **Compose Navigation** - Managing in-app navigation smoothly
- **Dagger Hilt** - Dependency injection for better scalability and testability
- **Material 3 UI** - Modern UI components for a polished look

## Challenges & Learnings
- Creating a good and interactive UI, which most common Tic Tac Toe games lack.
- Implementing the **Play with Friend** functionality, ensuring smooth two-sided gameplay.
- Ensuring the AI logic works effectively in medium and hard difficulty levels.
- Implementing **Pro Mode** for an enhanced and competitive experience.

## Limitations
- No online multiplayer mode (only local play is supported).
- No sound effects or vibrations to simulate win or lose.

## Next Milestones
- Implementing an online multiplayer mode.
- Adding more animations, sound effects, and vibrations.
- Adding a choice of grid sizes like **3x3, 4x4** for more engaging gameplay.

## Video Demo Link - [Video Demo](https://drive.google.com/file/d/1scReO-U4rhjHB_DC0QiUuthUKTsNGhjL/view?usp=sharing)

## Working APK Link - [APK](https://drive.google.com/file/d/1unX1lnb4Smyym60v1BZ4woApne4UiKgz/view?usp=sharing)
