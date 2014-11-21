def betar():
    yr =0.000003
    N =112
    K = 98   
    br = 0.00002
    Br = yr * (N-K)+br

    return Br
    
def betaw():
    yw =0.000002    
    N =112
    K = 98
    bw = 0.00003    
     
    Bw = yw * (N-K)+bw
 
    return Bw


      
def main():
    S = 100
    Iw = 10
    Ir = 2
    J = 10
    r = 0.002
    c = 0.0005
    D = 0.00001
    d = 0.00003
    z = 0.00005
    f = 2
    u = 0.5 
    K = 98
    N =112

    Br = betar()
    Bw = betaw()
    
    DsDt = J- Bw* S* Iw - Br* S* Ir - f* S + r * Iw + c* Ir - D * S
    DiDt = Bw * S * Iw - r * Iw - u * Iw - d*Iw - D*Iw - (z *Iw)
    DIrDt = Bw*S*Ir - c*Ir + u*Iw - z*Ir - D*Ir - (z*Ir)
  
    
    print "Ds/Dt = ", DsDt
    print "DI/Dt = ", DiDt
    print "DIr/Dt= ", DIrDt
main()
