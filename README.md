# 🌏 An avatar universe based text adventure 

A text-based interactive adventure game set in the Avatar universe where players choose an element (Fire, Water, Earth, or Air) and navigate through a branching narrative with unique abilities, moral choices, and multiple endings.

## 📋 Table of Contents
- [Overview](#overview)
- [Architecture](#architecture)
- [File Structure](#file-structure)
- [Design Patterns](#design-patterns)
- [How to Run](#how-to-run)
- [Technical Highlights](#technical-highlights)

---

## 🎮 Overview

This project demonstrates advanced Object-Oriented Programming principles through an interactive storytelling game. Players experience different narratives based on their element choice, with each path featuring unique abilities, moral dilemmas, and consequences.


### Element-Specific Gameplay
- **🔥 Fire Bending**: Lightning generation, combustion bending, fire healing
- **💧 Water Bending**: Healing abilities, blood bending (forbidden), spirit connection
- **🌍 Earth Bending**: Metal bending, lava bending, seismic sense
- **💨 Air Bending**: Flight, sky bison bonding, spiritual enlightenment

### Dynamic Story Paths
- **Aggressive Path**: Combat mastery, powerful techniques, warrior endings
- **Diplomatic Path**: Peacemaking, alliance building, negotiation endings
- **Spiritual Path**: Meditation, spirit world, transcendence endings
- **Dark Path**: Forbidden techniques, corruption, tyrant endings

### Multiple Endings
1. 🕊️ **Peace Ending** - Diplomatic victory
2. ⚔️ **Victory Ending** - Combat triumph
3. ✨ **Enlightenment Ending** - Spiritual transcendence
4. 💀 **Dark Lord Ending** - Corruption and tyranny
5. 💀 **Defeat Ending** - Loss in battle

---

## 🏗️ Architecture

The project follows **Model-View-Controller (MVC)** architecture with additional layers for story management and command processing.

```
┌─────────────────────────────────────────────────────────┐
│                    User Interface Layer                 │
│  ┌─────────┐  ┌──────────┐  ┌─────────────────────┐     │
│  │  Main   │→ │   Game   │→ │     Controller      │     │
│  └─────────┘  └──────────┘  └─────────────────────┘     │
│                                        ↓                │
│  ┌─────────────────────────────────────────────────┐    │
│  │                  View (Viewable)                │    │
│  └─────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│                   Business Logic Layer                  │
│  ┌─────────┐  ┌────────┐  ┌─────────┐  ┌──────────┐     │
│  │  World  │→ │  Story │→ │  Scene  │  │  Model   │     │
│  └─────────┘  └────────┘  └─────────┘  └──────────┘     │
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│                  Command Processing Layer               │
│  ┌─────────┐  ┌──────────────────┐  ┌──────────────┐    │
│  │ Parser  │→ │ CommandDictionary │→│   Command    │    │
│  └─────────┘  └──────────────────┘  └──────────────┘    │
└─────────────────────────────────────────────────────────┘
                           ↕
┌─────────────────────────────────────────────────────────┐
│                    Data Structure Layer                 │
│  ┌──────────────┐  ┌────────┐  ┌──────────────────┐     │
│  │AlphabeticList│→ │  Node  │→ │     Element      │     │
│  │    (BST)     │  └────────┘  └──────────────────┘     │
│  └──────────────┘                                       │
│         ↑                                               │
│  ┌──────────────┐                                       │
│  │AlphabeticSort│                                       │
│  │   (Hasher)   │                                       │
│  └──────────────┘                                       │
└─────────────────────────────────────────────────────────┘
```

---

## 📁 File Structure

### Core Game Management

#### **Main.java**
**Purpose**: Entry point of the application  
**Design Decision**: Minimal main method following Single Responsibility Principle
```java
public static void main(String[] args) {
    Game game = new Game();
    game.start();
}
```

#### **Game.java**
**Purpose**: Initializes the game world and starts the controller  
**Design Decision**: Factory pattern - creates and wires dependencies
- Instantiates `World` (game data)
- Instantiates `View` (display layer)
- Creates `Controller` and delegates control

**Why this matters**: Centralized initialization point makes testing and modification easier.

---

### MVC Components

#### **Controller.java** (The Orchestrator)
**Purpose**: Main game loop and coordination between Model, View, and Story  
**Design Decision**: Single Responsibility - only handles input/output coordination

**Key Responsibilities:**
- Manages game loop
- Receives user input via Scanner
- Delegates parsing to `Parser`
- Tracks statistics via `Model`
- Updates `View` with results
- Processes commands through `Story`

**Why this design**: Separates I/O concerns from business logic, making the code testable and maintainable.

```java
// Clean game loop
while(true) {
    view.print(choices);
    input = scanner.nextLine();
    command = parser.parse(input);
    result = story.processCommand(command);
    view.print(result);
}
```

#### **Model.java** (Data Tracker)
**Purpose**: Stores game state and player statistics  
**Design Decision**: Pure data model with no business logic

**Tracks:**
- Current command
- Command count (total and invalid)
- Success rate
- Last actions
- Game active state

**Why this design**: Enables features like:
- Statistics display
- Help suggestions after 3+ failures
- Save/load capability (future)
- Analytics and replay systems

#### **View.java** (Display Layer)
**Purpose**: Handles all output to the console  
**Design Decision**: Implements `Viewable` interface for flexibility

**Interface Design Benefit**: Future GUI implementation can implement the same interface without changing game logic.

```java
public interface Viewable {
    void printWelcome();
    void printEndGame();
    void print(String input);
}
```

**Why this matters**: The game logic doesn't care *how* text is displayed, only *that* it's displayed.

---

### Story Management

#### **World.java** (Resource Manager)
**Purpose**: Initializes and holds all game resources  
**Design Decision**: Composite pattern - aggregates related objects

**Manages:**
- Scene database (`SceneFactory.buildAllScenes()`)
- Command dictionary (verb/noun mappings)
- Alphabetic list (command lookup structure)
- Story instance (narrative manager)

**Why this design**: Single source of truth for game data. Controller only needs one `World` reference.

#### **Story.java** (Narrative Engine)
**Purpose**: Manages scene transitions and player progression  
**Design Decision**: State Machine pattern

**Key Features:**
- Tracks current scene
- Processes commands and transitions
- Auto-advances through cutscenes
- Applies effects (flags, stats)
- Handles save/load

**State Machine Logic:**
```java
processCommand() {
    validate command → 
    check current scene transitions → 
    apply effects → 
    move to next scene → 
    auto-advance if needed → 
    return new scene
}
```

**Why this design**: Encapsulates all story logic in one place. Controller doesn't need to understand scene graphs.

#### **Scene.java** (Story Node)
**Purpose**: Represents a single story moment with choices  
**Design Decision**: Graph node with transition map

**Structure:**
```java
Scene {
    String id;              // Unique identifier
    String title;           // Display name
    String description;     // Story text
    Map<String, String> transitions;  // verb+noun → nextSceneId
    List<String> choices;   // Human-readable options
}
```

**Key Method - Transition Lookup:**
```java
getNextId(verb, noun) {
    // Try exact match: "attack enemy"
    key = verb + "|" + noun;
    if (transitions.has(key)) return next;
    
    // Fallback to verb-only: "meditate"
    key = verb + "|";
    return transitions.get(key);
}
```

**Why this design**:
- Flexible matching (with/without nouns)
- Easy to add new transitions
- Self-contained scene logic

#### **SceneFactory.java** (Content Builder)
**Purpose**: Creates all 42 game scenes and connections  
**Design Decision**: Factory pattern with fluent API

**Design Pattern:**
```java
Map<String, Scene> scenes = new HashMap<>();

// Fluent builder pattern
add = (scene) -> {
    scenes.put(scene.getId(), scene);
    return scene;
};

// Build and wire scenes
add.apply(new Scene(...)).addChoice("verb", "noun", "nextId");
```

**Why this design**:
- Separates content from code logic
- Easy to add new scenes
- Clear visual graph structure
- Can be replaced with JSON/XML loader later

#### **abstractScene.java** (Interface Contract)
**Purpose**: Defines scene contract  
**Design Decision**: Abstract class for extensibility

**Future Possibilities:**
- `CombatScene` - with HP/damage
- `PuzzleScene` - with inventory checks
- `DialogueScene` - with NPC conversations

**Why abstract class vs interface**: Allows shared implementation in future while enforcing contract.

---

### Command Processing

#### **Parser.java** (Input Interpreter)
**Purpose**: Converts raw text input into structured Command objects  
**Design Decision**: Single Responsibility - only parsing

**Parsing Strategy:**
```
"attack the enemy" →
    Split on first space →
    verbRaw = "attack"
    nounRaw = "the enemy"
    ↓
    Canonicalize using CommandDictionary →
    verb = "attack"
    noun = "enemy"
    ↓
    Create Command(verb, noun)
```

**Multi-word Noun Support**: "attack ancient scroll" → preserves "ancient scroll" as one noun

**Why this design**: Separates parsing logic from validation. Easy to add new parsing rules.

#### **Command.java** (Data Structure)
**Purpose**: Simple data container for verb-noun pair  
**Design Decision**: Plain Old Java Object (POJO)

**Why separate class**:
- Type safety (can't mix up verb/noun order)
- Can add metadata later (timestamp, source, etc.)
- Clear API: `command.getVerb()` vs `String[] parts`

#### **CommandDictionary.java** (Synonym Mapper)
**Purpose**: Maps player input variations to canonical forms  
**Design Decision**: HashMap-based lookup with synonym support

**Example Mappings:**
```java
// Verbs
"go", "move", "step", "travel" → "walk"
"hit", "strike", "fight" → "attack"

// Nouns
"flame", "flames", "🔥" → "fire"
"master", "teacher", "guide" → "mentor"
```

**Canonicalization Algorithm:**
```java
canonicalizeNoun("the big gate") {
    1. Try full phrase: "the big gate" ❌
    2. Try from end: "big gate" ❌
    3. Try last word: "gate" ✅
    return "gate"
}
```

**Why this design**:
- Better UX (multiple ways to say same thing)
- Maintains single source of truth (canonical form)
- Easy to add synonyms without changing game logic

---

### Data Structures

#### **AlphabeticList.java** (BST Implementation)
**Purpose**: Binary Search Tree for O(log n) command lookup  
**Design Decision**: Hash-based BST with collision handling

**Structure:**
```
          Node(id=500, "walk")
           /              \
    Node(id=200)      Node(id=800, "attack")
       /                         \
  Node(id=100)              Node(id=900)
  └─ collisions: ["fire", "water"]  (hash collision)
```

**Collision Handling:**
- When hash(verb1) == hash(verb2), store in collision list
- Primary element in node
- Collisions in `List<Element>`

**Key Methods:**
- `insert(node)` - O(log n) average
- `findById(id)` - O(log n) lookup
- `findByIdAndName(id, name)` - Exact match with collision handling
- `findByName(name)` - O(n) fallback if hash lookup fails

**Why BST instead of HashMap**:
- **Educational**: Demonstrates tree implementation
- **Debugging**: Can traverse in order
- **Extension**: Can add range queries, prefix searches
- **Memory**: More cache-friendly for small datasets

#### **Node.java** (Tree Node)
**Purpose**: BST node with collision support  
**Design Decision**: Public fields for tree navigation

```java
class Node {
    Element e;              // Primary element
    int identifier;         // Hash value
    Node left, right;       // Tree pointers
    List<Element> collisions;  // Hash collision list
}
```

**Why public fields**: Allows AlphabeticList to directly manipulate tree structure. Trade-off: less encapsulation for simpler tree operations.

#### **Element.java** (Command Token)
**Purpose**: Represents a single verb or noun  
**Design Decision**: Simple data class with hash identifier

```java
class Element {
    String elementName;  // "walk", "fire", etc.
    int identifier;      // hash value
}
```

**Why separate class**: Allows future extension (priority, cost, cooldown, etc.)

#### **AlphabeticSort.java** (Hash Function)
**Purpose**: Converts strings to unique integer IDs  
**Design Decision**: Polynomial rolling hash

**Hash Algorithm:**
```java
hash(word) {
    h = 0
    for each char c in word.toLowerCase():
        h = (h * 131 + c + 1) % 1_000_000_007
    return h & 0x7fffffff  // Ensure positive
}
```

**Why this algorithm**:
- **Polynomial** (base 131): Good distribution
- **Prime modulo** (1000000007): Reduces collisions
- **Case-insensitive**: Converts to lowercase first
- **Non-negative**: Masks high bit for BST compatibility

**Collision probability**: Very low (< 0.1%) for typical game vocabulary

---

### Player State

#### **PlayerState.java** (Save System)
**Purpose**: Serializable player data for save/load  
**Design Decision**: Java Serialization for simplicity

**Stored Data:**
```java
class PlayerState {
    String name;
    String benderType;        // Fire/Water/Earth/Air
    String mentor;
    Set<String> flags;        // ["trained", "spiritual", ...]
    Set<String> inventory;    // Future: items collected
    String currentSceneId;    // For save/load
}
```

**Serialization:**
```java
save(path) {
    ObjectOutputStream.writeObject(this);
}

load(path) {
    return (PlayerState) ObjectInputStream.readObject();
}
```

**Why Sets**: Automatically handles duplicates. Can check `flags.contains("violent")` for branching logic.

**Future Extensions:**
- Track moral alignment score
- Store relationship values with NPCs
- Achievement system
- Playthrough statistics

---

## 🎯 Design Patterns Used

### 1. **Model-View-Controller (MVC)**
- **Model**: Game state and statistics
- **View**: Console output
- **Controller**: Input handling and orchestration

### 2. **Factory Pattern**
- `SceneFactory` creates all scenes
- `PlayerState.create()` factory method

### 3. **Strategy Pattern**
- `Viewable` interface allows different display strategies
- Easy to swap console → GUI

### 4. **State Machine**
- `Story` manages scene transitions
- Each scene represents a state

### 5. **Command Pattern**
- `Command` object encapsulates user actions
- Can add undo/redo later

### 6. **Singleton (implicit)**
- `World` creates single instance of resources
- `CommandDictionary` single source of truth

### 7. **Composite Pattern**
- `World` aggregates related objects
- `Scene` contains transitions and choices

---

## 🚀 How to Run

### Prerequisites
- Java JDK 11 or higher
- Any IDE (IntelliJ IDEA, Eclipse, VS Code)

### Running the Game

**Option 1: Command Line**
```bash
javac Main.java
java Main
```

**Option 2: IDE**
1. Open project in IDE
2. Run `Main.java`
3. Follow prompts in console

### Gameplay Commands

**Basic Format:** `verb [noun]`

**Examples:**
```
choose fire
attack torch
meditate
help village
talk enemy
walk forest
inspect scroll
```

**Special Commands:**
- `quit` / `exit` / `-1` - Exit game
- `restart game` - Start new playthrough

---

## 💡 Technical Highlights

### 1. **Efficient Command Lookup**
Using BST instead of linear search:
- **O(n)** linear search → **O(log n)** BST search
- Matters for extensibility (100+ commands)

### 2. **Flexible Scene Graph**
Adding new scenes doesn't require code changes in Controller/Story:
```java
// Just add to SceneFactory
scenes.get("NewScene").addChoice("verb", "noun", "NextScene");
```

### 3. **Smart Noun Parsing**
Handles multi-word nouns: "ancient stone gate" → "gate"

### 4. **Auto-Advance System**
Cutscenes flow automatically:
```java
S01_Prologue → S02_ChooseElement (no input needed)
```

### 5. **Collision Handling**
Hash collisions don't break the game - stored in lists

### 6. **Element-Specific Content**
Different scenes per element = 4x replay value

### 7. **Statistics Tracking**
Model tracks success rate, suggests help after failures

---

## 📚 Learning Outcomes

### Object-Oriented Design
- **Encapsulation**: Data hiding in Model, Scene, etc.
- **Inheritance**: abstractScene base class
- **Polymorphism**: Viewable interface
- **Abstraction**: Separating interface from implementation

### Data Structures
- **Binary Search Tree**: Efficient lookup
- **HashMap**: Fast key-value storage
- **Graph**: Scene transition network

### Design Patterns
- **MVC**: Clean separation of concerns
- **Factory**: Object creation abstraction
- **State Machine**: Complex state management
- **Command**: Action encapsulation

### Software Engineering Principles
- **Single Responsibility**: Each class has one job
- **Open/Closed**: Open for extension, closed for modification
- **Dependency Inversion**: Depend on abstractions (Viewable)
- **DRY**: Don't Repeat Yourself

---

## 👤 Author

**Rayyan** - *Initial work and design*

**Built with:** Java, passion for storytelling, and way too much coffee ☕

---

## 🙏 Acknowledgments

- Avatar: The Last Airbender creators for the inspiring universe
- The Java community for excellent documentation
- Everyone who playtested and provided feedback

---

**⭐ If you found this project interesting, please give it a star!**