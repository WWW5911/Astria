#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int all;
    vector<int> arr;
    cin>>all;

    for(int mm = 0; mm<all; mm++){
        arr.clear();
        int num, M, K, minn[10000][2] = {1001}, cont = 0;//0是數量 1是數字ㄝ
        bool hole[10000] = {false}, flag = false, test = false;
        cin>>num>>M>>K;
        arr.push_back(1);
        arr.push_back(1);
        arr.push_back(2);
        arr.push_back(3);
        int temp[10000];
        for(int i = 0; i<10000; i++)
            temp[i] = 0;
        temp[1]++;
        temp[2]++;
        temp[3]++;
     //   for(int i = 0; i<K+10; i++)
     //       cout<<temp[i]<<" ";

        for(int i = 4; i<num; i++){
            int tempp = ((arr[(i-1)] + arr[(i-2)] + arr[(i-3)] )% M + 1);
            arr.push_back( tempp );
            if(tempp <= K )
                temp[tempp]++;
        }
        for(int i = 1; i <= K ; i++){
       //     cout<<"到這"<<temp[i]<<endl;
            if(temp[i] == 0) {
       //         cout<<temp[i]<<" !!!!!!";
                test = true;
                cout<<"Case "<<mm+1<<": sequence nai"<<endl;
                break;
            }
            }
        if(test) continue;
        test = false;
        for(int i = 1; i <= K ; i++){
        //        cout<<i<<"目前探測 "<<temp[i]<<endl;
            if(minn[cont][0] > temp[i] ){
                minn[cont][1] = i;
                minn[cont][0] = temp[i];
              //  if(temp[i] == 0) break;
            }
        }
      //  cout<<"我最小的是" <<minn[0][0]<<endl;
        if(minn[0][1] == 0) {
            cout<<"Case "<<mm+1<<": sequence nai";
            continue;
        }
        for(int i = 1; i <= K ; i++){
          //  cout<<i<<"目前探測 "<<temp[i]<<"現在數字"<<minn[cont][0]<<endl;
            if(minn[cont][0] == temp[i] && minn[cont][1] != i ){
         //       cout<<i<<"目前探測 "<<temp[i]<<endl;
                cont++;
                minn[cont][1] = i;
                minn[cont][0] = minn[cont-1][0];
            }
        }
   //     for(int n = 0; n<=cont; n++)
    //        cout<<minn[n][1];
    int shi = 0;
        for(int i = K; i<num; i++){//窗格長度
            cout<<"現在要找長度 "<<i<<endl;
            for(int j = 1; j<num; j++){//找數字的位置
               // cout<<">>現在看的位置 " << j <<endl;
                for(int n = 0; n<=cont; n++){//比對數字是不是我要的
               //     cout<<">>>>>>>>>>>>>>>>>>>我要找的數字是 "<<minn[n][1]<<endl;
                    if(arr[j] == minn[n][1]){//如果是 那就開始移動窗格
                        for(int k = 0;  k<i; k++){ //K是位移量 如果J+K(目前數字+位移量) 小於num以及J-K大於等於0 且位移量要小於窗格量
                                if( (j+k)>=num || (j-k)<0 ) break;
                                for(int aaa = 1; aaa<=K; aaa++){
                        //            cout<<hole[aaa]<<" ";
                                    hole[aaa] = false;
                                }
                            for(int l = 0 ; l<i; l++){//l是窗格內的位置 從0~i-- 也就是從 J-I+K+1(位置-窗格長度+位移量+1) 到 J+K(目前位置+位移量)

                                while( j-i+k+l+1+shi < 0 ){
                                    shi++;
                                }
                         //       cout<<">>>>我的窗格在哪 "<<j-i+k+l+1+shi <<"這個數字是 "<<arr[ j-i+k+l+1+shi ]<<endl;
                                if(arr[ j-i+k+l+1+shi ]  <= K)
                                    hole[ arr[ j-i+k+l+1+shi ] ] = true;
                          //      cout<<hole[ 1  ] <<endl;
                            }
                            shi = 0;
                            int countt = 0;
                            for(int ans = 1; ans<=K; ans++){//檢查1~K的數字齊全了嗎
                       //         cout<<hole[ans]<<endl;
                                if( hole[ans] == false ){
                                break;
                                }
                                if(hole[ans])
                                    countt++;
                                if(countt == K){
                        //            cout<<"到K了"<<endl;
                                    flag = true;
                                }
                                if(flag){
                                    cout<<"Case "<<mm+1<<": "<<i<<endl;
                      //              cout<<">>>>>>找到解答 長度為 "<<i<<endl;
                                }
                            }
                            if(flag) break;
                        }
                        if(flag) break;
                    }
                    if(flag) break;
                }
                if(flag) break;

            }
            if(flag) break;
        }
        for(int ans = 1; ans<=K; ans++)
            hole[ans] == false;
        flag = false;

        delete [] temp;
    }
    return 0;
}
