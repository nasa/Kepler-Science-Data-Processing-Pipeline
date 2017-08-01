function [maskedSmearColsToExcludeDueToSpilledCharge, channelsWithBadCols] = ...
    get_masked_smear_columns_to_exclude(season, channel)
%
% Function to extract the 1-based CCD column indices where the masked smear is
% corrupted by spilled charge from a saturated star. There is a set of
% columns for each channel (possibly empty) for each season.
% Inputs:
%   season: 0-3 Kepler season 0=summer (Q2,Q6,Q10,...)
%   channel: 1-84 focal plane channel number
%
% Outputs:
%   maskedSmearColsToExcludeDueToSpilledCharge: column array of 1-based
%   column numbers to exclude, possibly empty
%
% autogenerated by auto_generate_cal_bad_smear_table.m
% 4/1/2014 - edited by Doug Caldwell to add masked smear columns under
%        CH-Cyg in Q14M1: season=0, channel=81, cols = [ 631 632 633 634 635 636 ]
% 
% Copyright 2017 United States Government as represented by the
% Administrator of the National Aeronautics and Space Administration.
% All Rights Reserved.
% 
% NASA acknowledges the SETI Institute's primary role in authoring and
% producing the Kepler Data Processing Pipeline under Cooperative
% Agreement Nos. NNA04CC63A, NNX07AD96A, NNX07AD98A, NNX11AI13A,
% NNX11AI14A, NNX13AD01A & NNX13AD16A.
% 
% This file is available under the terms of the NASA Open Source Agreement
% (NOSA). You should have received a copy of this agreement with the
% Kepler source code; see the file NASA-OPEN-SOURCE-AGREEMENT.doc.
% 
% No Warranty: THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY
% WARRANTY OF ANY KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY,
% INCLUDING, BUT NOT LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE
% WILL CONFORM TO SPECIFICATIONS, ANY IMPLIED WARRANTIES OF
% MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR FREEDOM FROM
% INFRINGEMENT, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL BE ERROR
% FREE, OR ANY WARRANTY THAT DOCUMENTATION, IF PROVIDED, WILL CONFORM
% TO THE SUBJECT SOFTWARE. THIS AGREEMENT DOES NOT, IN ANY MANNER,
% CONSTITUTE AN ENDORSEMENT BY GOVERNMENT AGENCY OR ANY PRIOR RECIPIENT
% OF ANY RESULTS, RESULTING DESIGNS, HARDWARE, SOFTWARE PRODUCTS OR ANY
% OTHER APPLICATIONS RESULTING FROM USE OF THE SUBJECT SOFTWARE.
% FURTHER, GOVERNMENT AGENCY DISCLAIMS ALL WARRANTIES AND LIABILITIES
% REGARDING THIRD-PARTY SOFTWARE, IF PRESENT IN THE ORIGINAL SOFTWARE,
% AND DISTRIBUTES IT "AS IS."
% 
% Waiver and Indemnity: RECIPIENT AGREES TO WAIVE ANY AND ALL CLAIMS
% AGAINST THE UNITED STATES GOVERNMENT, ITS CONTRACTORS AND
% SUBCONTRACTORS, AS WELL AS ANY PRIOR RECIPIENT. IF RECIPIENT'S USE OF
% THE SUBJECT SOFTWARE RESULTS IN ANY LIABILITIES, DEMANDS, DAMAGES,
% EXPENSES OR LOSSES ARISING FROM SUCH USE, INCLUDING ANY DAMAGES FROM
% PRODUCTS BASED ON, OR RESULTING FROM, RECIPIENT'S USE OF THE SUBJECT
% SOFTWARE, RECIPIENT SHALL INDEMNIFY AND HOLD HARMLESS THE UNITED
% STATES GOVERNMENT, ITS CONTRACTORS AND SUBCONTRACTORS, AS WELL AS ANY
% PRIOR RECIPIENT, TO THE EXTENT PERMITTED BY LAW. RECIPIENT'S SOLE
% REMEDY FOR ANY SUCH MATTER SHALL BE THE IMMEDIATE, UNILATERAL
% TERMINATION OF THIS AGREEMENT.
%
 


switch season
	case{0} 
		channelsWithBadCols = [... 
			1; 5; 6; 8; 9; 17; 18; 19; 20; 21; 22; 23; 24; 25; ...
			26; 27; 28; 30; 31; 36; 43; 49; 50; 51; 52; 53; 55; 56; ...
			57; 65; 66; 67; 68; 73; 81; 82; ]; 
		maskedSmearSpilledChargeCols = {...
			[ 227 ];... 
			[ 338 339 340 341 342 ];... 
			[ 339 340 ];... 
			[ 339 340 ];... 
			[ 72 73 ];... 
			[ 193 194 ];... 
			[ 193 194 ];... 
			[ 193 194 ];... 
			[ 192 193 194 195 196 197 ];... 
			[ 64 65 66 67 660 ];... 
			[ 63 64 65 66 67 68 69 522 ];... 
			[ 65 66 ];... 
			[ 65 66 ];... 
			[ 326 327 328 961 962 963 964 965 966 967 968 ];... 
			[ 325 326 327 328 329 964 ];... 
			[ 327 328 963 964 ];... 
			[ 328 962 963 964 965 966 ];... 
			[ 754 755 ];... 
			[ 285 ];... 
			[ 432 ];... 
			[ 19 20 48 76 ];... 
			[ 532 533 ];... 
			[ 49 532 ];... 
			[ 531 532 533 534 944 ];... 
			[ 531 532 533 534 ];... 
			[ 416 417 ];... 
			[ 715 ];... 
			[ 416 652 ];... 
			[ 607 ];... 
			[ 1112 ];... 
			[ 1111 1112 ];... 
			[ 1112 ];... 
			[ 1112 ];... 
			[ 865 1012 1013 ];... 
			[ 631 632 633 634 635 636 ];...
			[ 538 539 540 ];... 
			}; 

	case{1} 
		channelsWithBadCols = [... 
			5; 7; 8; 17; 18; 19; 20; 23; 24; 27; 29; 30; 31; 32; ...
			33; 37; 41; 42; 43; 44; 53; 55; 56; 57; 58; 60; 68; 69; ...
			73; ]; 
		maskedSmearSpilledChargeCols = {...
			[ 535 536 537 ];... 
			[ 535 536 537 538 ];... 
			[ 534 535 536 537 538 539 ];... 
			[ 227 228 322 323 967 968 969 970 971 972 973 ];... 
			[ 77 320 321 322 323 324 325 968 969 970 ];... 
			[ 321 322 323 969 ];... 
			[ 322 323 968 969 970 971 ];... 
			[ 217 218 219 220 221 ];... 
			[ 218 219 ];... 
			[ 790 ];... 
			[ 539 540 541 ];... 
			[ 538 539 540 541 542 ];... 
			[ 285 539 540 ];... 
			[ 539 ];... 
			[ 338 ];... 
			[ 660 ];... 
			[ 645 741 ];... 
			[ 741 ];... 
			[ 19 20 48 76 645 742 ];... 
			[ 645 646 740 741 742 743 744 ];... 
			[ 416 417 ];... 
			[ 715 ];... 
			[ 416 ];... 
			[ 194 ];... 
			[ 193 194 960 ];... 
			[ 192 193 194 195 196 197 ];... 
			[ 711 712 713 ];... 
			[ 409 410 ];... 
			[ 865 1012 1013 ];... 
			}; 

	case{2} 
		channelsWithBadCols = [... 
			1; 4; 9; 10; 17; 31; 33; 35; 36; 39; 40; 43; 53; 54; ...
			55; 56; 57; 58; 59; 60; 65; 66; 67; 68; 73; 79; ]; 
		maskedSmearSpilledChargeCols = {...
			[ 635 636 637 638 639 ];... 
			[ 637 ];... 
			[ 415 416 ];... 
			[ 715 ];... 
			[ 227 228 ];... 
			[ 285 ];... 
			[ 536 537 ];... 
			[ 536 537 538 ];... 
			[ 536 537 538 ];... 
			[ 219 220 221 ];... 
			[ 626 ];... 
			[ 19 20 48 76 ];... 
			[ 416 417 748 749 750 ];... 
			[ 747 748 749 750 751 ];... 
			[ 631 715 748 749 ];... 
			[ 416 ];... 
			[ 321 322 323 967 968 969 970 971 972 973 ];... 
			[ 320 321 322 323 324 970 ];... 
			[ 321 323 968 969 ];... 
			[ 322 323 967 968 969 970 971 ];... 
			[ 190 191 ];... 
			[ 190 191 965 ];... 
			[ 190 191 ];... 
			[ 189 190 191 192 193 194 ];... 
			[ 547 548 865 1012 1013 ];... 
			[ 445 446 447 ];... 
			}; 

	case{3} 
		channelsWithBadCols = [... 
			9; 17; 25; 26; 27; 28; 30; 41; 42; 43; 44; 45; 46; 47; ...
			48; 49; 50; 51; 52; 53; 54; 55; 56; 62; 63; 64; 65; 66; ...
			67; 68; 73; 78; 80; 81; 82; 83; 84; ]; 
		maskedSmearSpilledChargeCols = {...
			[ 765 ];... 
			[ 227 228 ];... 
			[ 189 190 191 ];... 
			[ 190 ];... 
			[ 190 962 ];... 
			[ 188 189 190 191 192 193 ];... 
			[ 872 873 ];... 
			[ 642 643 644 ];... 
			[ 641 642 643 644 645 646 ];... 
			[ 19 20 48 76 642 643 ];... 
			[ 642 643 644 ];... 
			[ 70 71 72 73 ];... 
			[ 69 70 71 72 73 74 75 ];... 
			[ 71 72 178 ];... 
			[ 70 71 72 73 ];... 
			[ 334 335 336 337 338 339 ];... 
			[ 335 336 337 ];... 
			[ 329 330 331 332 333 334 335 336 337 338 ];... 
			[ 335 336 337 ];... 
			[ 173 174 416 417 533 534 635 636 637 638 639 ];... 
			[ 532 533 534 535 536 ];... 
			[ 533 534 715 ];... 
			[ 173 416 533 652 ];... 
			[ 223 ];... 
			[ 223 224 225 ];... 
			[ 223 ];... 
			[ 326 327 328 962 963 964 965 966 967 968 969 ];... 
			[ 325 326 327 328 329 330 964 965 ];... 
			[ 327 328 ];... 
			[ 326 327 328 963 964 965 966 ];... 
			[ 865 1012 1013 ];... 
			[ 532 ];... 
			[ 532 533 534 ];... 
			[ 754 ];... 
			[ 752 753 754 755 756 ];... 
			[ 753 ];... 
			[ 753 754 ];... 
			}; 

end 
ichannel = find(channelsWithBadCols == channel); 

if ~isempty(ichannel) 
	maskedSmearColsToExcludeDueToSpilledCharge = sort(maskedSmearSpilledChargeCols{ichannel}); 
else 
	maskedSmearColsToExcludeDueToSpilledCharge =[]; 
end 

return; 