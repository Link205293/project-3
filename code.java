// Project 3
// Garrett Stoddard
// Objects time

// globals

float left, right, top, bot, mid;
float cueX,  cueY,  cueDX,  cueDY;
//float yellow1X,  yellow1Y,  yellow1DX,  yellow1DY;
//float blue2X, blue2Y, blue2DX, blue2DY;
//float red3X,  red3Y,  red3DX,  red3DY;
float friction = .986;
// dummy check varibles
float button1X, button1Y = 50, button1q = 50, button1p = 20;
float checkX[] = new float[16];
//float checkY[] = new float[15];
float ballX[] = new float[16];
float ballY[] = new float[16];
float ballDX[] = new float[16];
float ballDY[] = new float[16];
// setup method

void setup()
{
  size( 1250, 750 );
  left = 75;
  right = width - 75;
  top = 150;
  bot = height - 75;
  mid = width/2;
  reset();
}

void reset()
{
  cueX = mid/2;
  cueY = (top + bot)/2;
  for( int i = 0; i < 16; i = i + 1)
  {
    checkX[i] = random(mid + 15, right - 15);
    ballY[i] = random(top + 15, bot - 15); 
    ballDX[i] = 0;
    ballDY[i] = 0;
  }
  // Checks the placement of the second ball and doesnt let it spawn untill it 
  // isn't on top of another ball
  for( int i = 0; i < 16; i = i + 1)
  {
    for( int j = i + 1; j < 16; j = j +1)
    {
      while(abs(checkX[i] - checkX[j]) < 15 )
       {
         checkX[i] = random(mid + 15, right - 15);
       }
       ballX[i] = checkX[i];
    }
  }
   
}

class Ball 
{
  //Properties
  float x, y, dx, dy;
  color c;
  float name;
  boolean striped;
  
  // constructor
  Ball( float tempX, float tempY, float tempDX, float tempDY, float tempName, color tempC)
    {
      name = tempName;
      x = tempX;
      y = tempY;
      dx = tempDX;
      dy = tempDY;
      c = tempC;
    }
  //methods
}



