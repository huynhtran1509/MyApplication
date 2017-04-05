package com.example.myapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText edtContent;
    Button btnWrite, btnRead, btnWrite2, btnDel;
    static final int READ_BLOCK_SIZE = 100;
    String mName, mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtContent = (EditText) findViewById(R.id.editText);
        btnRead = (Button) findViewById(R.id.button);
        btnWrite = (Button) findViewById(R.id.button2);
        btnWrite2 = (Button) findViewById(R.id.button3);
        btnDel = (Button) findViewById(R.id.button4);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                readFileStream("click open", "open success");
                readData2();
//                readFile("click open", "open success");
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFileStream("click write", "write success5");
//                writeFile("click write", "write success5");
            }
        });
        btnWrite2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFileStream("click write232", "write success000");
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del();
            }
        });

    }

    String ad = "";
    int count = 1;
    File patchFolder = null, fileLog = null;
    int t=0;

    public void writeFileStream(String name, String context) {

        try {

//            delFolder(5);
            for (int i=0; i<2;i++) {
                try {
                    if (fileLog.lastModified() - patchFolder.lastModified() > (1000 * 5)) {

                        patchFolder.delete();
                        fileLog.delete();
                    } //else fileLog.delete();
                } catch (Exception e) {
                }

                patchFolder = new File(Environment.getExternalStorageDirectory(), "LogCrastiNot");
                if (patchFolder.mkdirs())
                    patchFolder.mkdirs();

                fileLog = new File(patchFolder, "LogBugCr.txt");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss a");
                Date date = new Date();
                String as = simpleDateFormat.format(date);
                ad = count + "[" + as + "]: " + name + " :: " + context + "\n";

                if (i==1){
                    FileOutputStream outputStream;
                outputStream = new FileOutputStream(fileLog, true);
                outputStream.write(ad.getBytes());
                outputStream.close();
                }
            }
            String s;
//            try {
//                if (fileLog.lastModified() - patchFolder.lastModified() > (1000 * 5)) {
//
//                    patchFolder.delete();
//                    fileLog.delete();
//
//                } //else fileLog.delete();
//            } catch (Exception e) {
//            }
            s = String.valueOf((fileLog.lastModified() - patchFolder.lastModified()) / 1000);
//            Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
            count++;
            edtContent.setText(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void delFolder(int nums) {
        try {
            if (fileLog.lastModified() - patchFolder.lastModified() > (1000 * nums)) {
                fileLog.delete();
                patchFolder.delete();
            } else fileLog.delete();
        } catch (Exception e) {
        }
    }

//
//    public void writeFileStream2(String name, String context) {
////        String str = edtContent.getText().toString();
//        try {
//            File file = new File(mContext.getFilesDir(), "loglbug.txt");
//            FileOutputStream fOut = mContext.getApplicationContext().openFileOutput("loglbug.txt", mContext.MODE_PRIVATE);
////            FileOutputStream fOut = openFileOutput("myFile.txt", MODE_PRIVATE);
//
////                File sdCard = Environment.getExternalStorageDirectory();
////                File directory = new File(sdCard.getAbsolutePath() + "/MyFiles");
////                directory.mkdirs();
////                File file = new File(directory, "myFile.txt");
////                FileOutputStream fOut = new FileOutputStream(file);
//
//
//            OutputStreamWriter osw = new
//                    OutputStreamWriter(fOut);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss a");
//            Date date = new Date();
//            String as = simpleDateFormat.format(date);
//
//            ad = ad + "[" + as + "]: " + name + ":: " + context + "\n";
//            //---Bắt đầu quá trình ghi file---
//            osw.write(ad);
//            osw.flush();
//            osw.close();
//
//            //---Hiển thị ra là đã lưu thành công---
//            Toast.makeText(mContext.getApplicationContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//
//    }

    public void readFileStream(String name, String context) {
        try {
//            FileInputStream fIn = openFileInput("a.txt");
            FileInputStream fIn = (FileInputStream) getResources().openRawResource(R.raw.a);
//            InputStream in= getResources().openRawResource(R.drawable.);
//        InputStreamReader inreader=new InputStreamReader(in);
//        BufferedReader bufreader=new BufferedReader(inreader);
//        StringBuilder builder=new StringBuilder();

//                File sdCard = Environment.getExternalStorageDirectory();
//                File directory = new File (sdCard.getAbsolutePath() + "/a");
//                File file = new File(directory, "a.txt");
//                FileInputStream fIn = new FileInputStream(file);
//                InputStreamReader isr = new InputStreamReader(fIn);

            InputStreamReader isr = new InputStreamReader(fIn);

            char[] inputBuffer = new char[READ_BLOCK_SIZE];
            String s = "";

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                //---Chuyển từ kiểu char sang string---
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                s += readString;
                inputBuffer = new char[READ_BLOCK_SIZE];
            }
            //---set text vừa đọc ra lên ô nhập liệu
            // read---
            edtContent.setText(s);
            Toast.makeText(getBaseContext(), "File loaded successfully!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void del() {
        try {
            FileInputStream fIn = openFileInput("a.txt");
            InputStreamReader isr = new InputStreamReader(fIn);

            InputStream in = new FileInputStream(String.valueOf(fIn));
            Reader fr = new InputStreamReader(in);
//            BufferReader br = new BufferedReader(r);

//            FileReader fr = new FileReader("a.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            int i = 0;
            while ((s = br.readLine()) != null) {
                i++;
//                System.out.println(i + " : " + s);
                if (i > 4) {
                    FileOutputStream fOut = openFileOutput("a.txt", MODE_PRIVATE);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);
                    osw.write("");
                    osw.flush();
                    osw.close();

                    Toast.makeText(getBaseContext(),
                            "File delete successfully!",
                            Toast.LENGTH_SHORT).show();
                }
            }
            br.close();
//            while (s=isr.read())


        } catch (Exception e) {
        }
    }

    public void readFile(String name, String context) {
        try {
            FileReader fr = new FileReader("a.txt");
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            int i = 0;
            while ((s = br.readLine()) != null) {
                i++;
//                System.out.println(i + " : " + s);
            }
            br.close();

            Toast.makeText(getBaseContext(), "File loaded successfully! " + i, Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeFile(String name, String context) {
        try {
            FileWriter fw = new FileWriter("a.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss a");
            Date date = new Date();
            String as = simpleDateFormat.format(date);

            ad = ad + "[" + as + "]: " + name + ":: " + context + "\n";
            bw.write(ad);
            bw.newLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /*
         * hàm này là đọc Resource File, Tôi gom chung vào đây
         */
    public void readData2() {
        String data;
        InputStream in = getResources()
                .openRawResource(R.raw.a);
        InputStreamReader inreader = new InputStreamReader(in);
        BufferedReader bufreader = new BufferedReader(inreader);
        StringBuilder builder = new StringBuilder();
        if (in != null) {
            try {
                while ((data = bufreader.readLine()) != null) {
                    builder.append(data);
                    builder.append("\n");
                }
                in.close();
                edtContent.setText(builder.toString());
            } catch (IOException ex) {
                Log.e("ERROR", ex.getMessage());
            }
        }
    }

}
