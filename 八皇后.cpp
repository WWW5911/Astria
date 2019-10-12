#include <iostream>
//8皇后 DFS
using namespace std;

int ans[100][10] = { 0 };//column = row
int wrong[10][3] = { 0 };//第一項為col 第二項為無法使用數(row, row-col, row+col)
int num = 0;
void QueenSearch(int column) {//column往後(為第二項) 可用的資料 ：ow之前沒使用過 row+col之前沒出現過 row-col之前沒出現過 如果檢查通過 紀錄不可使用 call下一個
	int row;
	bool canUse = true;
	for (row = 1; row <= 8; row++) {
		canUse = true;
		for (int j = 1; j < column; j++) {//測試可用與否 
			if (row == wrong[j][0]) canUse = false;
			if(row - column == wrong[j][1]) canUse = false;
			if (row + column == wrong[j][2]) canUse = false;
		}
		if (canUse) {
			ans[num][column] = row;
			if (column < 8) {
				wrong[column][0] = row;
				wrong[column][1] = row - column;
				wrong[column][2] = row + column;
//				cout <<">>"<< column << " - " << row << " 可以使用" << endl;
				QueenSearch(column + 1);
			}
			else {
				num++;
				for (int i = 1; i <= 8; i++)
					ans[num][i] = ans[num - 1][i];
//				cout << "找到正解" << endl;
//				for (int i = 0; i < 100; i++) {
//					for (int j = 0; j < 10; j++)
//						cout << ans[i][j] << " ";
//					cout << endl;
//				}
			} 
		} 
//		else cout << column << " - " << row << " 無法使用" << endl;
	}
//	cout <<">>"<< column << "列搜尋完 無可用" << endl;
	if (column == 1) {//最後一筆無法做完
		for (int i = 0; i < 10; i++)
			ans[num][i] = 0;
	}
	return;
	/*整體邏輯：
		從row = 0開始
		先進行不可使用確認
		確認可以使用的情況下
			無論怎麼樣進行ans撰寫
			1.若column 小於結尾(此為8) 
				進行不可使用清單撰寫
				呼叫本function column + 1
			2.若column 等於結尾(此為8) 代表搜尋到正確解
				num++ 代表第num個解答找到 要找下一個
				將++前的解答複製到++後
		以上做完回到上一次
	*/
}
int main()
{
  QueenSearch(1);
}
