#include <iostream>
#include <vector>
#include <algorithm>//使用這個的sort
#include <math.h>

#include <time.h>
// UVA 1494 最小生成樹
using namespace std;

struct City {
	int id;
	int x, y;
	int people;
	City(int a, int b, int c):x(a), y(b), people(c){}
};

struct Edge {
	City p1, p2;
	int cost;
	double allpeople;
	bool used = false;
	Edge();
	Edge(City a, City b):used(false), p1(a), p2(b), allpeople(a.people + b.people), cost( sqrt(pow(p1.x - p2.x, 2) + pow(p1.y - p2.y, 2) ) ){}
};
bool camp(Edge a, Edge b) {
	if (a.cost != b.cost)
		return a.cost < b.cost;//如果比較小會排在前面
	else return a.allpeople > b.allpeople;
}

int find(int* disjoin, int num) {
	int k = num;
	while (disjoin[k] >= 0) {
		k = disjoin[k];
	}
	return k;//傳集合頭
}



int kurskal(int count, int point, vector<Edge>& E) {
	int ans = 0;
	double max = 0;
	int time = 0, start = 0, end = 0;
	int *disjoin = new int[point];
	
	for (int i = 0; i < point; i++)
		disjoin[i] = -1;
	sort(E.begin(), E.end(), camp);

	for (int i = 0; i < count; i++) {
		if (time >= point - 1) break;
		int faP1 = find(disjoin, E[i].p1.id);
		int faP2 = find(disjoin, E[i].p2.id);

		if (faP1 != faP2) {
			//無論如何 把P1加入P2
			disjoin[faP2] += disjoin[faP1];
			disjoin[faP1] = E[i].p2.id;

			time++;
			E[i].used = true;
			for (int i = 0; i < point; i++) {
				cout << i << "  " << disjoin[i] << endl;
			}
			cout << endl;
			
		}
	}
	double road = 0;
	int id = 0;
	for (int i = 0; i < E.size(); i++) {
		if (E[i].used == false) continue;
	//	cout << E[i].p1.id << " " << E[i].p2.id << " " << E[i].cost << " " << E[i].allpeople << " " << endl;

		for (unsigned int j = 0; j < E.size(); j++) {//j代表其他的路 這邊我要算手動修的總路長
			if (j == i) continue;
			if (E[j].used == false) continue;
			road += E[j].cost;
		}

		double temp = E[i].allpeople / road;
		//cout << E[i].allpeople / road << endl;
		if (temp > max) { max = temp; id = i; }
		road = 0;
	}
//	cout << E[id].p1.id << " " << E[id].p2.id << " " << E[id].cost << " " << E[id].allpeople << " " << endl;
	printf("%.2f\n", max);

	delete[] disjoin;
	return ans;
}



int main()
{
	vector<City> city;
	vector<Edge> edge;
	int all, num;
	City tempc(0 ,0 ,0);

	cin >> all;

	clock_t start, end;
	start = clock();

	for (int mm = 0; mm < all; mm++) {
		int count = 0;
		cin >> num;
		for (int i = 0; i < num; i++) {
			cin >> tempc.x >> tempc.y >> tempc.people;
			tempc.id = i;
			city.push_back(tempc);
		}
		for (int i = 0; i < num; i++)
			for (int j = i + 1; j < num; j++) {
				if (j >= num) break;
				Edge tempe(city[i], city[j]);
				edge.push_back(tempe);
				count++;
			}
		kurskal(count, num, edge);

		city.clear();
		edge.clear();
	}
	
	end = clock();
	double diff = end - start; 
	printf("執行速度 %f  ms", diff);
	printf(" %f  sec", diff / CLOCKS_PER_SEC);
}
