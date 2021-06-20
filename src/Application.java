import java.util.*;

public class Application {
	public static void main(String[] args) {
		float PF_VL[] = {0,0 , 0,1 , 10,1 , 30,0};
		float PF_L[] = {10,0 , 30,1 , 40,1 , 60,0};
		float PF_M[] = {40,0 , 60,1 , 70,1 , 90,0};
		float PF_H[] = {70,0 , 90,1 , 100,1 , 100,0};
		
		float TXL_B[] = {0,0 , 15,1 , 30,0};
		float TXL_I[] = {15,0 , 30,1 , 45,0};
		float TXL_E[] = {30,0 , 60,1 , 60,0};
		
		float R_H[] = {0,0 , 25,1 , 50,0};
		float R_N[] = {25,0 , 50,1 , 75,0};
		float R_L[] = {50,0 , 100,1 , 100,0};
		
		float PF, TXL;
		
		Scanner scanner = new Scanner(System.in);
		PF = scanner.nextFloat();
		TXL = scanner.nextFloat();
		scanner.close();
		
		float PF_Hv = Utils.fuzzify(PF_H, PF);
		float PF_Mv = Utils.fuzzify(PF_M, PF);
		float PF_VLv = Utils.fuzzify(PF_VL, PF);
		float PF_Lv = Utils.fuzzify(PF_L, PF);
		
		float TXL_Bv = Utils.fuzzify(TXL_B, TXL);
		float TXL_Iv = Utils.fuzzify(TXL_I, TXL);
		float TXL_Ev = Utils.fuzzify(TXL_E, TXL);
		
		float r1 = Math.max(PF_Hv, TXL_Ev);
		float r2 = Math.max(Math.min(PF_Mv, TXL_Iv), TXL_Bv);
		float r3 = PF_VLv;
		float r4 = Math.min(PF_Lv, TXL_Bv);
		
		float predictedRisk = Utils.defuzzify(r1, r2, r3, r4, R_H, R_N, R_L);
		
		float R_Hv = Utils.fuzzify(R_H, predictedRisk);
		float R_Nv = Utils.fuzzify(R_N, predictedRisk);
		float R_Lv = Utils.fuzzify(R_L, predictedRisk);
		
		System.out.println("Predicted value (risk) = " + predictedRisk);
		if(R_Hv > R_Nv) {
			System.out.println("Risk will be High");
		}else if(R_Nv > R_Lv) {
			System.out.println("Risk will be Normal");
		}else {
			System.out.println("Risk will be Low");
		}
	}

}
