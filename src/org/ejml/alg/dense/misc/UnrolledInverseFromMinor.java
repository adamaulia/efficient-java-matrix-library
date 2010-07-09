/*
 * Copyright (c) 2009-2010, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * EJML is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * EJML is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with EJML.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.ejml.alg.dense.misc;

import org.ejml.data.DenseMatrix64F;


/**
 * This code was auto generated by  {@link GenerateInverseFromMinor} and should not be modified
 * directly.  The input matrix is scaled make it much less prone to overflow and underflow issues.
 * 
 * @author Peter Abeles
 */
public class UnrolledInverseFromMinor {
    
    public static final int MAX = 5;
    
    public static void inv( DenseMatrix64F mat , DenseMatrix64F inv ) {
        double max = Math.abs(mat.data[0]);
        int N = mat.getNumElements();
        
        for( int i = 1; i < N; i++ ) {
            double a = Math.abs(mat.data[i]);
            if( a > max ) max = a;
        }

        if( mat.numRows == 2 ) {
            inv2(mat,inv,max);
        } else if( mat.numRows == 3 ) {
            inv3(mat,inv,max);            
        } else if( mat.numRows == 4 ) {
            inv4(mat,inv,max);            
        } else if( mat.numRows == 5 ) {
            inv5(mat,inv,max);            
        } else {
            throw new IllegalArgumentException("Not supported");
        }
    }

    public static void inv2( DenseMatrix64F mat , DenseMatrix64F inv , double div )
    {
        double []data = mat.data;

        double a11 = data[ 0 ]/div;
        double a12 = data[ 1 ]/div;
        double a21 = data[ 2 ]/div;
        double a22 = data[ 3 ]/div;

        double m11 = a22;
        double m12 = -( a21);
        double m21 = -( a12);
        double m22 = a11;

        double det = (a11*m11 + a12*m12)*div;

        data = inv.data;
        data[0] = m11 / det;
        data[1] = m21 / det;
        data[2] = m12 / det;
        data[3] = m22 / det;

    }

    public static void inv3( DenseMatrix64F mat , DenseMatrix64F inv , double div )
    {
        double []data = mat.data;

        double a11 = data[ 0 ]/div;
        double a12 = data[ 1 ]/div;
        double a13 = data[ 2 ]/div;
        double a21 = data[ 3 ]/div;
        double a22 = data[ 4 ]/div;
        double a23 = data[ 5 ]/div;
        double a31 = data[ 6 ]/div;
        double a32 = data[ 7 ]/div;
        double a33 = data[ 8 ]/div;

        double m11 = a22*a33 - a23*a32;
        double m12 = -( a21*a33 - a23*a31);
        double m13 = a21*a32 - a22*a31;
        double m21 = -( a12*a33 - a13*a32);
        double m22 = a11*a33 - a13*a31;
        double m23 = -( a11*a32 - a12*a31);
        double m31 = a12*a23 - a13*a22;
        double m32 = -( a11*a23 - a13*a21);
        double m33 = a11*a22 - a12*a21;

        double det = (a11*m11 + a12*m12 + a13*m13)*div;

        data = inv.data;
        data[0] = m11 / det;
        data[1] = m21 / det;
        data[2] = m31 / det;
        data[3] = m12 / det;
        data[4] = m22 / det;
        data[5] = m32 / det;
        data[6] = m13 / det;
        data[7] = m23 / det;
        data[8] = m33 / det;

    }

    public static void inv4( DenseMatrix64F mat , DenseMatrix64F inv , double div )
    {
        double []data = mat.data;

        double a11 = data[ 0 ]/div;
        double a12 = data[ 1 ]/div;
        double a13 = data[ 2 ]/div;
        double a14 = data[ 3 ]/div;
        double a21 = data[ 4 ]/div;
        double a22 = data[ 5 ]/div;
        double a23 = data[ 6 ]/div;
        double a24 = data[ 7 ]/div;
        double a31 = data[ 8 ]/div;
        double a32 = data[ 9 ]/div;
        double a33 = data[ 10 ]/div;
        double a34 = data[ 11 ]/div;
        double a41 = data[ 12 ]/div;
        double a42 = data[ 13 ]/div;
        double a43 = data[ 14 ]/div;
        double a44 = data[ 15 ]/div;

        double m11 =  + a22*(a33*a44 - a34*a43) - a23*(a32*a44 - a34*a42) + a24*(a32*a43 - a33*a42);
        double m12 = -(  + a21*(a33*a44 - a34*a43) - a23*(a31*a44 - a34*a41) + a24*(a31*a43 - a33*a41));
        double m13 =  + a21*(a32*a44 - a34*a42) - a22*(a31*a44 - a34*a41) + a24*(a31*a42 - a32*a41);
        double m14 = -(  + a21*(a32*a43 - a33*a42) - a22*(a31*a43 - a33*a41) + a23*(a31*a42 - a32*a41));
        double m21 = -(  + a12*(a33*a44 - a34*a43) - a13*(a32*a44 - a34*a42) + a14*(a32*a43 - a33*a42));
        double m22 =  + a11*(a33*a44 - a34*a43) - a13*(a31*a44 - a34*a41) + a14*(a31*a43 - a33*a41);
        double m23 = -(  + a11*(a32*a44 - a34*a42) - a12*(a31*a44 - a34*a41) + a14*(a31*a42 - a32*a41));
        double m24 =  + a11*(a32*a43 - a33*a42) - a12*(a31*a43 - a33*a41) + a13*(a31*a42 - a32*a41);
        double m31 =  + a12*(a23*a44 - a24*a43) - a13*(a22*a44 - a24*a42) + a14*(a22*a43 - a23*a42);
        double m32 = -(  + a11*(a23*a44 - a24*a43) - a13*(a21*a44 - a24*a41) + a14*(a21*a43 - a23*a41));
        double m33 =  + a11*(a22*a44 - a24*a42) - a12*(a21*a44 - a24*a41) + a14*(a21*a42 - a22*a41);
        double m34 = -(  + a11*(a22*a43 - a23*a42) - a12*(a21*a43 - a23*a41) + a13*(a21*a42 - a22*a41));
        double m41 = -(  + a12*(a23*a34 - a24*a33) - a13*(a22*a34 - a24*a32) + a14*(a22*a33 - a23*a32));
        double m42 =  + a11*(a23*a34 - a24*a33) - a13*(a21*a34 - a24*a31) + a14*(a21*a33 - a23*a31);
        double m43 = -(  + a11*(a22*a34 - a24*a32) - a12*(a21*a34 - a24*a31) + a14*(a21*a32 - a22*a31));
        double m44 =  + a11*(a22*a33 - a23*a32) - a12*(a21*a33 - a23*a31) + a13*(a21*a32 - a22*a31);

        double det = (a11*m11 + a12*m12 + a13*m13 + a14*m14)*div;

        data = inv.data;
        data[0] = m11 / det;
        data[1] = m21 / det;
        data[2] = m31 / det;
        data[3] = m41 / det;
        data[4] = m12 / det;
        data[5] = m22 / det;
        data[6] = m32 / det;
        data[7] = m42 / det;
        data[8] = m13 / det;
        data[9] = m23 / det;
        data[10] = m33 / det;
        data[11] = m43 / det;
        data[12] = m14 / det;
        data[13] = m24 / det;
        data[14] = m34 / det;
        data[15] = m44 / det;

    }

    public static void inv5( DenseMatrix64F mat , DenseMatrix64F inv , double div )
    {
        double []data = mat.data;

        double a11 = data[ 0 ]/div;
        double a12 = data[ 1 ]/div;
        double a13 = data[ 2 ]/div;
        double a14 = data[ 3 ]/div;
        double a15 = data[ 4 ]/div;
        double a21 = data[ 5 ]/div;
        double a22 = data[ 6 ]/div;
        double a23 = data[ 7 ]/div;
        double a24 = data[ 8 ]/div;
        double a25 = data[ 9 ]/div;
        double a31 = data[ 10 ]/div;
        double a32 = data[ 11 ]/div;
        double a33 = data[ 12 ]/div;
        double a34 = data[ 13 ]/div;
        double a35 = data[ 14 ]/div;
        double a41 = data[ 15 ]/div;
        double a42 = data[ 16 ]/div;
        double a43 = data[ 17 ]/div;
        double a44 = data[ 18 ]/div;
        double a45 = data[ 19 ]/div;
        double a51 = data[ 20 ]/div;
        double a52 = data[ 21 ]/div;
        double a53 = data[ 22 ]/div;
        double a54 = data[ 23 ]/div;
        double a55 = data[ 24 ]/div;

        double m11 =  + a22*( + a33*(a44*a55 - a45*a54) - a34*(a43*a55 - a45*a53) + a35*(a43*a54 - a44*a53)) - a23*( + a32*(a44*a55 - a45*a54) - a34*(a42*a55 - a45*a52) + a35*(a42*a54 - a44*a52)) + a24*( + a32*(a43*a55 - a45*a53) - a33*(a42*a55 - a45*a52) + a35*(a42*a53 - a43*a52)) - a25*( + a32*(a43*a54 - a44*a53) - a33*(a42*a54 - a44*a52) + a34*(a42*a53 - a43*a52));
        double m12 = -(  + a21*( + a33*(a44*a55 - a45*a54) - a34*(a43*a55 - a45*a53) + a35*(a43*a54 - a44*a53)) - a23*( + a31*(a44*a55 - a45*a54) - a34*(a41*a55 - a45*a51) + a35*(a41*a54 - a44*a51)) + a24*( + a31*(a43*a55 - a45*a53) - a33*(a41*a55 - a45*a51) + a35*(a41*a53 - a43*a51)) - a25*( + a31*(a43*a54 - a44*a53) - a33*(a41*a54 - a44*a51) + a34*(a41*a53 - a43*a51)));
        double m13 =  + a21*( + a32*(a44*a55 - a45*a54) - a34*(a42*a55 - a45*a52) + a35*(a42*a54 - a44*a52)) - a22*( + a31*(a44*a55 - a45*a54) - a34*(a41*a55 - a45*a51) + a35*(a41*a54 - a44*a51)) + a24*( + a31*(a42*a55 - a45*a52) - a32*(a41*a55 - a45*a51) + a35*(a41*a52 - a42*a51)) - a25*( + a31*(a42*a54 - a44*a52) - a32*(a41*a54 - a44*a51) + a34*(a41*a52 - a42*a51));
        double m14 = -(  + a21*( + a32*(a43*a55 - a45*a53) - a33*(a42*a55 - a45*a52) + a35*(a42*a53 - a43*a52)) - a22*( + a31*(a43*a55 - a45*a53) - a33*(a41*a55 - a45*a51) + a35*(a41*a53 - a43*a51)) + a23*( + a31*(a42*a55 - a45*a52) - a32*(a41*a55 - a45*a51) + a35*(a41*a52 - a42*a51)) - a25*( + a31*(a42*a53 - a43*a52) - a32*(a41*a53 - a43*a51) + a33*(a41*a52 - a42*a51)));
        double m15 =  + a21*( + a32*(a43*a54 - a44*a53) - a33*(a42*a54 - a44*a52) + a34*(a42*a53 - a43*a52)) - a22*( + a31*(a43*a54 - a44*a53) - a33*(a41*a54 - a44*a51) + a34*(a41*a53 - a43*a51)) + a23*( + a31*(a42*a54 - a44*a52) - a32*(a41*a54 - a44*a51) + a34*(a41*a52 - a42*a51)) - a24*( + a31*(a42*a53 - a43*a52) - a32*(a41*a53 - a43*a51) + a33*(a41*a52 - a42*a51));
        double m21 = -(  + a12*( + a33*(a44*a55 - a45*a54) - a34*(a43*a55 - a45*a53) + a35*(a43*a54 - a44*a53)) - a13*( + a32*(a44*a55 - a45*a54) - a34*(a42*a55 - a45*a52) + a35*(a42*a54 - a44*a52)) + a14*( + a32*(a43*a55 - a45*a53) - a33*(a42*a55 - a45*a52) + a35*(a42*a53 - a43*a52)) - a15*( + a32*(a43*a54 - a44*a53) - a33*(a42*a54 - a44*a52) + a34*(a42*a53 - a43*a52)));
        double m22 =  + a11*( + a33*(a44*a55 - a45*a54) - a34*(a43*a55 - a45*a53) + a35*(a43*a54 - a44*a53)) - a13*( + a31*(a44*a55 - a45*a54) - a34*(a41*a55 - a45*a51) + a35*(a41*a54 - a44*a51)) + a14*( + a31*(a43*a55 - a45*a53) - a33*(a41*a55 - a45*a51) + a35*(a41*a53 - a43*a51)) - a15*( + a31*(a43*a54 - a44*a53) - a33*(a41*a54 - a44*a51) + a34*(a41*a53 - a43*a51));
        double m23 = -(  + a11*( + a32*(a44*a55 - a45*a54) - a34*(a42*a55 - a45*a52) + a35*(a42*a54 - a44*a52)) - a12*( + a31*(a44*a55 - a45*a54) - a34*(a41*a55 - a45*a51) + a35*(a41*a54 - a44*a51)) + a14*( + a31*(a42*a55 - a45*a52) - a32*(a41*a55 - a45*a51) + a35*(a41*a52 - a42*a51)) - a15*( + a31*(a42*a54 - a44*a52) - a32*(a41*a54 - a44*a51) + a34*(a41*a52 - a42*a51)));
        double m24 =  + a11*( + a32*(a43*a55 - a45*a53) - a33*(a42*a55 - a45*a52) + a35*(a42*a53 - a43*a52)) - a12*( + a31*(a43*a55 - a45*a53) - a33*(a41*a55 - a45*a51) + a35*(a41*a53 - a43*a51)) + a13*( + a31*(a42*a55 - a45*a52) - a32*(a41*a55 - a45*a51) + a35*(a41*a52 - a42*a51)) - a15*( + a31*(a42*a53 - a43*a52) - a32*(a41*a53 - a43*a51) + a33*(a41*a52 - a42*a51));
        double m25 = -(  + a11*( + a32*(a43*a54 - a44*a53) - a33*(a42*a54 - a44*a52) + a34*(a42*a53 - a43*a52)) - a12*( + a31*(a43*a54 - a44*a53) - a33*(a41*a54 - a44*a51) + a34*(a41*a53 - a43*a51)) + a13*( + a31*(a42*a54 - a44*a52) - a32*(a41*a54 - a44*a51) + a34*(a41*a52 - a42*a51)) - a14*( + a31*(a42*a53 - a43*a52) - a32*(a41*a53 - a43*a51) + a33*(a41*a52 - a42*a51)));
        double m31 =  + a12*( + a23*(a44*a55 - a45*a54) - a24*(a43*a55 - a45*a53) + a25*(a43*a54 - a44*a53)) - a13*( + a22*(a44*a55 - a45*a54) - a24*(a42*a55 - a45*a52) + a25*(a42*a54 - a44*a52)) + a14*( + a22*(a43*a55 - a45*a53) - a23*(a42*a55 - a45*a52) + a25*(a42*a53 - a43*a52)) - a15*( + a22*(a43*a54 - a44*a53) - a23*(a42*a54 - a44*a52) + a24*(a42*a53 - a43*a52));
        double m32 = -(  + a11*( + a23*(a44*a55 - a45*a54) - a24*(a43*a55 - a45*a53) + a25*(a43*a54 - a44*a53)) - a13*( + a21*(a44*a55 - a45*a54) - a24*(a41*a55 - a45*a51) + a25*(a41*a54 - a44*a51)) + a14*( + a21*(a43*a55 - a45*a53) - a23*(a41*a55 - a45*a51) + a25*(a41*a53 - a43*a51)) - a15*( + a21*(a43*a54 - a44*a53) - a23*(a41*a54 - a44*a51) + a24*(a41*a53 - a43*a51)));
        double m33 =  + a11*( + a22*(a44*a55 - a45*a54) - a24*(a42*a55 - a45*a52) + a25*(a42*a54 - a44*a52)) - a12*( + a21*(a44*a55 - a45*a54) - a24*(a41*a55 - a45*a51) + a25*(a41*a54 - a44*a51)) + a14*( + a21*(a42*a55 - a45*a52) - a22*(a41*a55 - a45*a51) + a25*(a41*a52 - a42*a51)) - a15*( + a21*(a42*a54 - a44*a52) - a22*(a41*a54 - a44*a51) + a24*(a41*a52 - a42*a51));
        double m34 = -(  + a11*( + a22*(a43*a55 - a45*a53) - a23*(a42*a55 - a45*a52) + a25*(a42*a53 - a43*a52)) - a12*( + a21*(a43*a55 - a45*a53) - a23*(a41*a55 - a45*a51) + a25*(a41*a53 - a43*a51)) + a13*( + a21*(a42*a55 - a45*a52) - a22*(a41*a55 - a45*a51) + a25*(a41*a52 - a42*a51)) - a15*( + a21*(a42*a53 - a43*a52) - a22*(a41*a53 - a43*a51) + a23*(a41*a52 - a42*a51)));
        double m35 =  + a11*( + a22*(a43*a54 - a44*a53) - a23*(a42*a54 - a44*a52) + a24*(a42*a53 - a43*a52)) - a12*( + a21*(a43*a54 - a44*a53) - a23*(a41*a54 - a44*a51) + a24*(a41*a53 - a43*a51)) + a13*( + a21*(a42*a54 - a44*a52) - a22*(a41*a54 - a44*a51) + a24*(a41*a52 - a42*a51)) - a14*( + a21*(a42*a53 - a43*a52) - a22*(a41*a53 - a43*a51) + a23*(a41*a52 - a42*a51));
        double m41 = -(  + a12*( + a23*(a34*a55 - a35*a54) - a24*(a33*a55 - a35*a53) + a25*(a33*a54 - a34*a53)) - a13*( + a22*(a34*a55 - a35*a54) - a24*(a32*a55 - a35*a52) + a25*(a32*a54 - a34*a52)) + a14*( + a22*(a33*a55 - a35*a53) - a23*(a32*a55 - a35*a52) + a25*(a32*a53 - a33*a52)) - a15*( + a22*(a33*a54 - a34*a53) - a23*(a32*a54 - a34*a52) + a24*(a32*a53 - a33*a52)));
        double m42 =  + a11*( + a23*(a34*a55 - a35*a54) - a24*(a33*a55 - a35*a53) + a25*(a33*a54 - a34*a53)) - a13*( + a21*(a34*a55 - a35*a54) - a24*(a31*a55 - a35*a51) + a25*(a31*a54 - a34*a51)) + a14*( + a21*(a33*a55 - a35*a53) - a23*(a31*a55 - a35*a51) + a25*(a31*a53 - a33*a51)) - a15*( + a21*(a33*a54 - a34*a53) - a23*(a31*a54 - a34*a51) + a24*(a31*a53 - a33*a51));
        double m43 = -(  + a11*( + a22*(a34*a55 - a35*a54) - a24*(a32*a55 - a35*a52) + a25*(a32*a54 - a34*a52)) - a12*( + a21*(a34*a55 - a35*a54) - a24*(a31*a55 - a35*a51) + a25*(a31*a54 - a34*a51)) + a14*( + a21*(a32*a55 - a35*a52) - a22*(a31*a55 - a35*a51) + a25*(a31*a52 - a32*a51)) - a15*( + a21*(a32*a54 - a34*a52) - a22*(a31*a54 - a34*a51) + a24*(a31*a52 - a32*a51)));
        double m44 =  + a11*( + a22*(a33*a55 - a35*a53) - a23*(a32*a55 - a35*a52) + a25*(a32*a53 - a33*a52)) - a12*( + a21*(a33*a55 - a35*a53) - a23*(a31*a55 - a35*a51) + a25*(a31*a53 - a33*a51)) + a13*( + a21*(a32*a55 - a35*a52) - a22*(a31*a55 - a35*a51) + a25*(a31*a52 - a32*a51)) - a15*( + a21*(a32*a53 - a33*a52) - a22*(a31*a53 - a33*a51) + a23*(a31*a52 - a32*a51));
        double m45 = -(  + a11*( + a22*(a33*a54 - a34*a53) - a23*(a32*a54 - a34*a52) + a24*(a32*a53 - a33*a52)) - a12*( + a21*(a33*a54 - a34*a53) - a23*(a31*a54 - a34*a51) + a24*(a31*a53 - a33*a51)) + a13*( + a21*(a32*a54 - a34*a52) - a22*(a31*a54 - a34*a51) + a24*(a31*a52 - a32*a51)) - a14*( + a21*(a32*a53 - a33*a52) - a22*(a31*a53 - a33*a51) + a23*(a31*a52 - a32*a51)));
        double m51 =  + a12*( + a23*(a34*a45 - a35*a44) - a24*(a33*a45 - a35*a43) + a25*(a33*a44 - a34*a43)) - a13*( + a22*(a34*a45 - a35*a44) - a24*(a32*a45 - a35*a42) + a25*(a32*a44 - a34*a42)) + a14*( + a22*(a33*a45 - a35*a43) - a23*(a32*a45 - a35*a42) + a25*(a32*a43 - a33*a42)) - a15*( + a22*(a33*a44 - a34*a43) - a23*(a32*a44 - a34*a42) + a24*(a32*a43 - a33*a42));
        double m52 = -(  + a11*( + a23*(a34*a45 - a35*a44) - a24*(a33*a45 - a35*a43) + a25*(a33*a44 - a34*a43)) - a13*( + a21*(a34*a45 - a35*a44) - a24*(a31*a45 - a35*a41) + a25*(a31*a44 - a34*a41)) + a14*( + a21*(a33*a45 - a35*a43) - a23*(a31*a45 - a35*a41) + a25*(a31*a43 - a33*a41)) - a15*( + a21*(a33*a44 - a34*a43) - a23*(a31*a44 - a34*a41) + a24*(a31*a43 - a33*a41)));
        double m53 =  + a11*( + a22*(a34*a45 - a35*a44) - a24*(a32*a45 - a35*a42) + a25*(a32*a44 - a34*a42)) - a12*( + a21*(a34*a45 - a35*a44) - a24*(a31*a45 - a35*a41) + a25*(a31*a44 - a34*a41)) + a14*( + a21*(a32*a45 - a35*a42) - a22*(a31*a45 - a35*a41) + a25*(a31*a42 - a32*a41)) - a15*( + a21*(a32*a44 - a34*a42) - a22*(a31*a44 - a34*a41) + a24*(a31*a42 - a32*a41));
        double m54 = -(  + a11*( + a22*(a33*a45 - a35*a43) - a23*(a32*a45 - a35*a42) + a25*(a32*a43 - a33*a42)) - a12*( + a21*(a33*a45 - a35*a43) - a23*(a31*a45 - a35*a41) + a25*(a31*a43 - a33*a41)) + a13*( + a21*(a32*a45 - a35*a42) - a22*(a31*a45 - a35*a41) + a25*(a31*a42 - a32*a41)) - a15*( + a21*(a32*a43 - a33*a42) - a22*(a31*a43 - a33*a41) + a23*(a31*a42 - a32*a41)));
        double m55 =  + a11*( + a22*(a33*a44 - a34*a43) - a23*(a32*a44 - a34*a42) + a24*(a32*a43 - a33*a42)) - a12*( + a21*(a33*a44 - a34*a43) - a23*(a31*a44 - a34*a41) + a24*(a31*a43 - a33*a41)) + a13*( + a21*(a32*a44 - a34*a42) - a22*(a31*a44 - a34*a41) + a24*(a31*a42 - a32*a41)) - a14*( + a21*(a32*a43 - a33*a42) - a22*(a31*a43 - a33*a41) + a23*(a31*a42 - a32*a41));

        double det = (a11*m11 + a12*m12 + a13*m13 + a14*m14 + a15*m15)*div;

        data = inv.data;
        data[0] = m11 / det;
        data[1] = m21 / det;
        data[2] = m31 / det;
        data[3] = m41 / det;
        data[4] = m51 / det;
        data[5] = m12 / det;
        data[6] = m22 / det;
        data[7] = m32 / det;
        data[8] = m42 / det;
        data[9] = m52 / det;
        data[10] = m13 / det;
        data[11] = m23 / det;
        data[12] = m33 / det;
        data[13] = m43 / det;
        data[14] = m53 / det;
        data[15] = m14 / det;
        data[16] = m24 / det;
        data[17] = m34 / det;
        data[18] = m44 / det;
        data[19] = m54 / det;
        data[20] = m15 / det;
        data[21] = m25 / det;
        data[22] = m35 / det;
        data[23] = m45 / det;
        data[24] = m55 / det;

    }

}
