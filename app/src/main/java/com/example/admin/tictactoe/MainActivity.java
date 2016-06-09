package com.example.admin.tictactoe;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity
{
    private boolean noughtsTurn = false ;
    private char board[][] = new char[3][3] ;
    int player_X = 0 ;
    int player_0 = 0 ;
    TextView s1, s2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        s1 = (TextView) findViewById(R.id.textViewX) ;
        s2 = (TextView) findViewById(R.id.textView0) ;
        setuponClickListeners();
        resetButtons() ;
    }

    public void newGame(View view)
    {
        noughtsTurn = false ;
        board = new char[3][3] ;
        resetButtons() ;
        player_X = 0 ;
        player_0 = 0 ;
        s1.setText("0");
        s2.setText("0");
    }

    private void resetButtons()
    {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout) ;
        for(int y=0 ; y<T.getChildCount() ; y++)
        {
            if(T.getChildAt(y) instanceof TableRow)
            {
                TableRow R = (TableRow) T.getChildAt(y) ;
                for(int x = 0; x<R.getChildCount(); x++)
                {
                    if(R.getChildAt(x) instanceof Button)
                    {
                        Button B = (Button) R.getChildAt(x) ;
                        B.setText("");
                        B.setEnabled(true);
                    }
                }
            }
        }
        TextView t = (TextView) findViewById(R.id.titleText) ;
        t.setText(R.string.title);
    }

    private boolean checkWin()
    {
        char winner = '\0' ;
        if(checkWinner(board, 3, 'X'))
        {
            winner = 'X' ;
            player_X++ ;
            s1.setText(String.valueOf(player_X));
            noughtsTurn = false ;
            board = new char[3][3] ;
            resetButtons();
        }
        else if(checkWinner(board, 3, '0'))
        {
            winner = '0' ;
            player_0++ ;
            s2.setText(String.valueOf(player_0));
            noughtsTurn = false ;
            board = new char[3][3] ;
            resetButtons();
        }

        if(winner == '\0')
        {
            return false ;
        }
        else
        {
            TextView T = (TextView) findViewById(R.id.titleText) ;
            T.setText(winner + " wins");
            Toast.makeText(getBaseContext(),winner+ " wins" , Toast.LENGTH_LONG).show() ;
            resetButtons();
            return true ;
        }
    }

    private boolean checkWinner(char[][] board, int size, char player)
    {
        for(int x=0 ; x<size ; x++)
        {
            int total = 0 ;
            for(int y=0 ; y<size ; y++)
            {
                if(board[x][y] == player)
                {
                    total++ ;
                }
            }
            if(total >= size)
            {
                return true ;
            }
        }

        for(int y=0 ; y<size ; y++)
        {
            int total = 0 ;
            for(int x=0 ; x<size ; x++)
            {
                if(board[x][y] == player)
                {
                    total++ ;
                }
            }
            if(total >= size)
            {
                return true ;
            }
        }

        int total = 0 ;
        for(int x=0 ; x<size ; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (x == y && board[x][y] == player)
                {
                    total++;
                }
            }
        }
            if(total >= size)
            {
                return true ;
            }

        total = 0 ;
        for(int x=0 ; x<size ; x++)
        {
            for (int y = 0; y < size; y++)
            {
                if (x + y == 2 && board[x][y] == player)
                {
                    total++;
                }
            }
        }
        if(total >= size)
        {
            return true ;
        }
        return false ;
    }

    private void disableButtons()
    {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout) ;
        for(int y=0; y< T.getChildCount(); y++)
        {
            if(T.getChildAt(y) instanceof TableRow) {
                TableRow R = (TableRow) T.getChildAt(y);
                for (int x = 0; x < R.getChildCount(); x++)
                {
                    if(R.getChildAt(x) instanceof Button)
                    {
                        Button B = (Button) R.getChildAt(x) ;
                        B.setEnabled(false);
                    }
                }
            }
        }
    }

    private void setuponClickListeners()
    {
        TableLayout T = (TableLayout) findViewById(R.id.tableLayout) ;
        for(int y=0 ; y<T.getChildCount(); y++)
        {
            if(T.getChildAt(y) instanceof TableRow)
            {
                TableRow R = (TableRow) T.getChildAt(y) ;
                for(int x=0 ; x<R.getChildCount(); x++)
                {
                    View V = R.getChildAt(x) ;
                    V.setOnClickListener(new PlayOnClick(x,y));
                }
            }
        }
    }

    private class PlayOnClick implements View.OnClickListener
    {
        private int x=0 ;
        private int y=0 ;

        public PlayOnClick(int x,  int y)
        {
            this.x = x ;
            this.y = y ;
        }


        @Override
        public void onClick(View v)
        {
            if(v instanceof Button)
            {
                Button B = (Button) v ;
                board[x][y] = noughtsTurn ? '0' : 'X' ;
                B.setText(noughtsTurn ? "O"  : "X");

                B.setEnabled(false);
                noughtsTurn = !noughtsTurn ;

                if(checkWin())
                {

                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
