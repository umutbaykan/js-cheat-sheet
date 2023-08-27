// Config.json file
{
  "compilerOptions": {
    "strict": true,
    "outDir": "build"
  },
  "include": ["src/**/*.ts"]
}

// Basic Types
const films: string[] = [];
const filmms: {name: string, age:number} = {name: "john", age:20}

function isEven(n: number): boolean {
  return n % 2 === 0
}

const isEvenReally = (n: number): boolean => {
  return n % 2 === 0;
}

// Functions that deal with anonymous types
function flatten<T>(lists: T[][]): T[] {
  return lists.reduce((a, b) => [...a, ...b]);
}

//////// Type Aliasing
type Cat = {
  name: string,
  colour: string
  age?: number
};

const sigrid : Cat = { name: "Sigrid", colour: "Tortie" };

//The code above is the same as the following: 

const izzy : { name: string, colour: string } = { name: "Izzy", colour: "Black and white" };

type StringAlias = string;
type NumberAlias = number;

type StringOrNum = StringAlias | NumberAlias;

// allowing types in objects
const scoreSheet: {[key: string]: number} = { "A": 1, "B": 2 }

// Extending types
type Animal = {
  isWild: boolean 
}
// type Animal extends interface x would not compile, because types cannot be extended. 

interface Pet extends Animal {
  name: string, 
  age: number
}
// This works because Pet is an interface extending from the type Animal, not the other way around.

interface Cat extends Pet {
  colour: string,
  indoorsOnly: boolean
}
// Cat is an interface extending from another interface, and inherits everything from Pet, including what it took from Animal.

// Types cannot extend interfaces, interfaces can extend types


//////// Intersection Types
interface Pet {
  name: string, 
  age: number,
  colour: string,
}

interface Cat {
  indoorsOnly: boolean
}

type PetCat = Pet & Cat; 
// PetCat is { name: string, age: number, colour: string, indoorsOnly: boolean }

type Dog {
  likesBellyRubs: boolean;
}

type PetDog = Pet & Dog;
// You can mix types and interfaces with intersection type to build up custom types. 


/////// Enums
export enum PermissionLevel { 
	PERMISSION_LEVEL_ONE = 1,
	PERMISSION_LEVEL_TWO,
	PERMISSION_LEVEL_THREE
}


// Types can extend interfaces 
interface LengthProp { 
	length: number
}
const logInfo = <Type extends LengthProp>(arg: Type): void => { 
	console.log(arg.length)

////// Adding types into props
interface Props {
  updateScore: (score: number, index:number) => void;
  index: number;
  focusIndex: number;
}

const TextForm = ({updateScore, index, focusIndex}: Props) => {
  // do somehintg
}

