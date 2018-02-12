include Makefile

.PHONY: export

WWW := kelen.polytechnique.fr:/www/http/informatique/INF431/X10-2011-2012

export:
	make all
	make clean
	rm -f DATE && /bin/date > DATE
	cd .. && \
	tar cvhz \
	  --exclude=.svn \
	  --exclude="*~" \
	  --file=calculix.tar.gz \
	  calculix/DATE calculix/Makefile \
	  calculix/CalculiX calculix/examples calculix/lib && \
	scp calculix.tar.gz $(WWW)

# tar: -h means follow symbolic links.
# I have a symbolic link of calculix/CalculiX to calculix/src.

