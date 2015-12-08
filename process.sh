#/bin/sh
# find . -type f;
#
# grep -m 1 "<br>" 1175056AR01.html ;
# grep LFN -A 1 1175056AR01.html ;
# grep ">Inscrits<" -A 1 1175056AR01.html

if [ -z "$1" ]
  then
    echo "USAGE: ./process.sh parti"
    echo "EX = FN : LFN"
    exit
fi

echo "Recherche des fichiers et copie des resulats pour le $1"
find . -type f -regex ".*\.\(html\)" | xargs -L2 "cat" | grep -e ">Inscrits<" -e ">$1<" -A 1 > tempresults ;
# fichier de test
echo "parsing des resultats"
java ParseHtml $1 > tempresults.csv ;
#rm tempresults ;
echo "sort csv" ;
echo "date,value" > siteregion/$1.csv ;
cat tempresults.csv | sort  -k1,1n >> siteregion/$1.csv ;
#rm results.csv;
