; 數字分割&計算基個數、偶之和，並相乘
INCLUDE Irvine32.inc
.data
myID BYTE ?,? ,? ,? ,? ,? ,?,?,?,? ; student ID
sizeID BYTE ($-myID)
myID_oddCount BYTE 0
myID_evenSum BYTE 0
myID_result BYTE ?
temp Dword ?
.code
main PROC
	Call Readint
	XOR edi, edi
	XOR esi, esi
	L1: ;分割輸入
		XOR edx,edx		;清空EDX 
		MOV ecx,10
		DIV ecx			;EDX存餘數 商在EAX
		PUSH edx
		inc edi
		cmp eax, 0
		je L2
		jmp	L1
	L2: ;將分割的輸入存入陣列
		pop eax		
		mov myID[esi], al
		inc esi
		cmp esi, edi
		jne	L2
	xor esi, esi
	L3: ;計算基偶
		XOR edx,edx		;清空EDX 
		MOV ecx,2
		mov al, myID[esi]
		mov temp, eax
		DIV ecx
		cmp edx, 1
		je ODD
		jmp EVE
	ODD:;奇數計算有幾個
		mov al, myID_oddCount
		inc al
		mov myID_oddCount, al
		jmp L4
	EVE:;偶數累加
		mov al, myID_evenSum
		add eax, [temp]
		mov myID_evenSum, al
		jmp L4
	L4:;計算次數
		inc esi
		cmp esi, edi
		jne L3
	;將結果相乘
	xor esi, esi
	mov al , myID_oddCount
	mul myID_evenSum
	mov myID_result, al
	Call Writeint
	
	exit
main ENDP
END main
