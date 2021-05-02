/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Nam Dinh
 */
public class Main {
    public static HandlerDictionary listDic = new HandlerDictionary();

    public static void main(String[] args) throws IOException  {
        menu();
        listDic = new HandlerDictionary();
        listDic.readSlangFile();

        handleAction();
    }
    
    public static void menu() {
        System.out.println("\n\n---------------- MENU ----------------\n:");
        System.out.println("  1 Tim kiem theo slang word.");
        System.out.println("  2 Tim kiem theo definition.");
        System.out.println("  3 Hien lich su tim kiem.");
        System.out.println("  4 Them slang word.");
        System.out.println("  5 Chuc dang edit slang word.");
        System.out.println("  6 Chuc nang delete slang word.");
        System.out.println("  7 Chuc nang reset danh sach slang word.");
        System.out.println("  8 Chuc nang random 1 slang word.");
        System.out.println("  9 Chuc nang do vui (cho slang word, chon definition).");
        System.out.println(" 10 Chuc nang do vui (cho definition, chon slang word).");
        System.out.print("---------------- MENU ----------------");
    }

    public static void handleAction() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        while (true) {
            System.out.print("\n\nVui long nhap lua chon cua ban (nhap 0 de dung / nhap 'menu' de hien menu)): ");
            String key = reader.readLine();
            if (key.equals("0")) {
                System.out.print("\n------------- END ----------------");
                break;
            }
            else if (key.equals("menu")) {
                menu();
            }
            else if (key.equals("1")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG TIM KIEM THEO SLANG WORD ========================== \n\n");
                listDic.searchSlangWord();
            }
            else if (key.equals("2")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG TIM KIEM THEO DEFINITION ========================== \n\n");
                listDic.searchKey();
            }
            else if (key.equals("3")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG SHOW HISTORY ====================================== \n\n");
                listDic.showHistory();
            }
            else if (key.equals("4")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG THEM SLANG WORD =================================== \n\n");
                listDic.addSlangWord();
            }
            else if (key.equals("5")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG THEM SLANG WORD =================================== \n\n");
                listDic.editSlangWord();
            }
            else if (key.equals("6")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG DELETE SLANG WORD ================================= \n\n");
                listDic.deleteSlangWord();
            }
            else if (key.equals("7")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG RESET LIST SLANG WORD ============================= \n\n");
                listDic.reset();
            }
            else if (key.equals("8")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG RANDOM MOT SLANG WORD ============================= \n\n");
                listDic.randomSlang();
            }
            else if (key.equals("9")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG DOAN DEFINITION =================================== \n\n");
                listDic.definitionQuestion();
            }
            else if (key.equals("10")) {
                System.out.print("\n\n======================= BAN DA CHON CHUC NANG DOAN SLANG WORD =================================== \n\n");
                listDic.slangwordQuestion();
            }
        }
    }
}
